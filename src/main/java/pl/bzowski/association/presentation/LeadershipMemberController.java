package pl.bzowski.association.presentation;

import pl.bzowski.association.business.entity.LeadershipMember;
import pl.bzowski.association.presentation.util.JsfUtil;
import pl.bzowski.association.presentation.util.JsfUtil.PersistAction;
import pl.bzowski.association.business.boundary.LeadershipMemberFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@Named("leadershipMemberController")
@SessionScoped
public class LeadershipMemberController implements Serializable {


    @EJB private pl.bzowski.association.business.boundary.LeadershipMemberFacade ejbFacade;
    private List<LeadershipMember> items = null;
    private LeadershipMember selected;

    public LeadershipMemberController() {
    }

    public LeadershipMember getSelected() {
        return selected;
    }

    public void setSelected(LeadershipMember selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LeadershipMemberFacade getFacade() {
        return ejbFacade;
    }

    public LeadershipMember prepareCreate() {
        selected = new LeadershipMember();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/langs/i18n").getString("created"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/langs/i18n").getString("updated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/langs/i18n").getString("deleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<LeadershipMember> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/langs/i18n").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/langs/i18n").getString("PersistenceErrorOccured"));
            }
        }
    }

    public LeadershipMember getLeadershipMember(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<LeadershipMember> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<LeadershipMember> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass=LeadershipMember.class)
    public static class LeadershipMemberControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LeadershipMemberController controller = (LeadershipMemberController)facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "leadershipMemberController");
            return controller.getLeadershipMember(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof LeadershipMember) {
                LeadershipMember o = (LeadershipMember) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), LeadershipMember.class.getName()});
                return null;
            }
        }

    }

}
