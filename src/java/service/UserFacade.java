/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Device;
import bean.User;
import controler.util.JsfUtil;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.DeviceUtil;
import util.HashageUtil;

/**
 *
 * @author fatima
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "freelancerPU")
    private EntityManager em;
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    /*connecte User*/
    @EJB
    private DeviceFacade deviceFacade;
    public int seConnnecter(User user) {
        if (user == null || user.getLogin() == null) {
            JsfUtil.addErrorMessage("Veuilliez saisir votre login");
            return -5; // please type login
        } else {
            User loadedUser = find(user.getLogin());
            if (loadedUser == null) {
                return -4; // makaynch had user
            } else if (!loadedUser.getPasseword().equals(HashageUtil.sha256(user.getPasseword()))) {
                if (loadedUser.getNbrCnx() < 3) {
                    System.out.println("hana loadedUser.getNbrCnx() < 3 ::: " + loadedUser.getNbrCnx());
                    JsfUtil.addErrorMessage("Incorrect Password ! Try Again ! You have "+(3-loadedUser.getNbrCnx())+" left ");
                    loadedUser.setNbrCnx(loadedUser.getNbrCnx() + 1);
                } else if (loadedUser.getNbrCnx() >= 3) {
                    System.out.println("hana loadedUser.getNbrCnx() >= 3::: " + loadedUser.getNbrCnx());
                    loadedUser.setBlocked(1);
                    // edit(loadedUser);
                }
                JsfUtil.addErrorMessage("Wrong Passeword");
                return -3; // Wrong Password
            } else if (loadedUser.getBlocked() == 1) {
                JsfUtil.addErrorMessage("this user is blocked");
                return -2; // this user is blocked
            } else {
                loadedUser.setNbrCnx(0);
                edit(loadedUser);
                user = clone(loadedUser);
                user.setMdpChanged(loadedUser.isMdpChanged());
                Device device = DeviceUtil.getDevice();
                int res = deviceFacade.checkDevice(user,device );
                if ( res < 0 )
                {
                    return -6; // this device is not included
                }
                edit(user);
               // SessionUtil.attachUserToCommune(user);
                return 1; // this user is exist
            }
            
        }
     }
    
    public User clone(User user) {
        User clone = new User();
        clone.setLogin(user.getLogin());
        clone.setBlocked(user.getBlocked());
        clone.setMdpChanged(user.isMdpChanged());
        clone.setNbrCnx(user.getNbrCnx());
        clone.setPasseword(user.getPasseword());
        return clone;
    }
    
        public void inscrir(User selected) {
        User user = clone(selected);
        user.setPasseword(util.HashageUtil.sha256(user.getPasseword()));
        create(user);
    }
    /* Connecte User*/
}
    


