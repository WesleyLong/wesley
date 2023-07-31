package com.example.demo.register;

import org.springframework.context.ApplicationEvent;

public class Events {
    static class UserLogoutEvent extends ApplicationEvent {

        private String username;

        public UserLogoutEvent(Object source) {
            super(source);
        }

        public UserLogoutEvent(Object source, String username) {
            super(source);
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }

    static class UserRegisterEvent extends ApplicationEvent {

        private String username;

        public UserRegisterEvent(Object source) {
            super(source);
        }

        public UserRegisterEvent(Object source, String username) {
            super(source);
            this.username = username;
        }

        public String getUsername() {
            return username;
        }
    }
}
