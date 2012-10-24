package org.koala.locust.util.tag;

import java.util.ArrayList;
import java.util.List;

import org.koala.locust.domain.RungIn;
import org.koala.locust.entity.User;
import org.koala.locust.util.RungInLabel;

public class RungInBlock {

    private User user;

    private RungInLabel rungInLabel;

    private List<RungIn> rungIns = new ArrayList<RungIn>();

    public void addRungIn(RungIn r) {
        rungIns.add(r);
    }

    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RungInLabel getRungInLabel() {
        return rungInLabel;
    }

    public void setRungInLabel(RungInLabel rungInLabel) {
        this.rungInLabel = rungInLabel;
    }

    public List<RungIn> getRungIns() {
        return rungIns;
    }

    public void setRungIns(List<RungIn> rungIns) {
        this.rungIns = rungIns;
    }

}
