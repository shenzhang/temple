package temple.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * User: shenzhang
 * Date: 9/11/14
 * Time: 11:34 PM
 */
@Component
public class LoginListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {
    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
    }
}
