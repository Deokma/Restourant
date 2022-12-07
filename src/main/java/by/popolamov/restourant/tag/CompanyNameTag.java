package by.popolamov.restourant.tag;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.tagext.TagSupport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * The class CompanyNameTag.
 */
public class CompanyNameTag extends TagSupport {
    private static final Logger logger = LogManager.getLogger(CompanyNameTag.class);
    private static final String COMPANY_NAME = "&copy 2022 RST, Inc";

    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.write(COMPANY_NAME);
        } catch (IOException e) {
            logger.error("CompanyNameTag error: {}", e.getMessage(), e);
            throw new JspException("CompanyNameTag error: " + e.getMessage(), e);
        }
        return SKIP_BODY;
    }
}
