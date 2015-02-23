package pl.bzowski.association.presentation;

import pl.bzowski.association.business.entity.Meeting;
import pl.bzowski.association.presentation.util.JsfUtil;
import pl.bzowski.association.presentation.util.JsfUtil.PersistAction;
import pl.bzowski.association.business.boundary.MeetingFacade;

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
import javax.inject.Inject;
import org.primefaces.model.DualListModel;
import pl.bzowski.association.business.entity.AssociationMember;
import pl.bzowski.association.business.entity.Leadership;
import pl.bzowski.association.business.boundary.MemberAdder;

@Named("meetingController")
@SessionScoped
public class MeetingController implements Serializable {

    @EJB
    private pl.bzowski.association.business.boundary.MeetingFacade ejbFacade;
    
    @Inject
    private MemberAdder ma;
    
    private List<Meeting> items = null;
    private Meeting selected;
    private DualListModel<AssociationMember> leadershipMembers;

    public MeetingController() {
    }

    public Meeting getSelected() {
        return selected;
    }

    public void setSelected(Meeting selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MeetingFacade getFacade() {
        return ejbFacade;
    }

    public Meeting prepareCreate() {
        selected = new Meeting();
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

    public List<Meeting> getItems() {
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

    public Meeting getMeeting(java.lang.Long id) {
        return getFacade().find(id);
    }

    public List<Meeting> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Meeting> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public void prepareAddMember() {
        leadershipMembers = ma.prepareAddMember(selected);
    }

    public void saveMembers() {
        List<AssociationMember> source = leadershipMembers.getSource();
        List<AssociationMember> target = leadershipMembers.getTarget();
        ma.saveMeetingMembers(selected, source, target);
    }

    public DualListModel<AssociationMember> getLeadershipMembers() {
        return leadershipMembers;
    }

    public void setLeadershipMembers(DualListModel<AssociationMember> leadershipMembers) {
        this.leadershipMembers = leadershipMembers;
    }

    @FacesConverter(forClass = Meeting.class)
    public static class MeetingControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MeetingController controller = (MeetingController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "meetingController");
            return controller.getMeeting(getKey(value));
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
            if (object instanceof Meeting) {
                Meeting o = (Meeting) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Meeting.class.getName()});
                return null;
            }
        }

    }

}
