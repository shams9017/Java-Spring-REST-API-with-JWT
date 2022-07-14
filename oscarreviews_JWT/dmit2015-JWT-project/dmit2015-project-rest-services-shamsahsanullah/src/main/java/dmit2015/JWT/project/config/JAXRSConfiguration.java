/**
 * JAXRSConfiguration.java - this is the config class for the JAXRS.
 * @author  Shams Ahsanullah
 * @version 1.0
 */

package dmit2015.JWT.project.config;

import org.eclipse.microprofile.auth.LoginConfig;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("webapi")
@LoginConfig(authMethod="MP-JWT", realmName="MP JWT Realm")
@DeclareRoles({"USER","ADMIN"})
public class JAXRSConfiguration extends Application {

}
