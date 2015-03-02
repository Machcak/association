package pl.bzowski.association.presentation;


import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.context.FacesContext;

@Named("editorController")
@SessionScoped
public class EditorController implements Serializable {

	private static final long serialVersionUID = 20111020L;

	private String content = "";
	
	private String color = "#33fc14";

	public EditorController() {
	
	}

	public void saveListener() {
		content = content.replaceAll("\\r|\\n", "");

		final FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Content",
		    		content.length() > 150 ? content.substring(0, 100) : content);

		FacesContext.getCurrentInstance().addMessage(null, msg);
        }

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public String getColor() {
		return color;
	}

	public void setColor(final String color) {
		this.color = color;
	}

}
            