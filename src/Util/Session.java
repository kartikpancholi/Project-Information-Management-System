package Util;

public class Session
{
    static Session session = null;
    private String email = null;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static Session getSession()
    {
        if(session == null)
        {
            session = new Session();
            return session;
        }
        else
        {
            return session;
        }
    }

    public static void setSession(Session session) {
        Session.session = session;
    }
}
