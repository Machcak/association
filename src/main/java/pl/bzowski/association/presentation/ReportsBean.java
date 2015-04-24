package pl.bzowski.association.presentation;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
 
@Named
@SessionScoped
public class ReportsBean extends AbstractReportBean implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 5960378929459855033L;
	private final String COMPILE_FILE_NAME = "bilans_miesieczny_fizyczny";
 
    @Override
    protected String getCompileFileName() {
        return COMPILE_FILE_NAME;
    }
 
    @Override
    protected Map<String, Object> getReportParameters() {
        Map<String, Object> reportParameters = new HashMap<String, Object>();
 
        reportParameters.put("rtitle", "Hello JasperReports");
 
        return reportParameters;
    }
 
    public String execute() {
        try {
            super.prepareReport();
        } catch (Exception e) {
            // make your own exception handling
            e.printStackTrace();
        }
 
        return null;
    }
}
