/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Device;
import bean.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fatima
 */
@Stateless
public class DeviceFacade extends AbstractFacade<Device> {

    @PersistenceContext(unitName = "freelancerPU")
    private EntityManager em;

    
     public int checkDevice(User user,Device connectedDevice) {
        List<Device> list = findDeviceByUtilisateur(user);
        if (list.isEmpty()) {
            return 1; // it's a new device
        } else {
            for (int i = 0; i < list.size(); i++) {
                Device device = list.get(i);
                if (device.getDeviceCategorie().equals(connectedDevice.getDeviceCategorie())
                        && device.getBrowser().equals(connectedDevice.getBrowser())
                        && device.getOperatingSystem().equals(connectedDevice.getOperatingSystem())) {
                    return 2; // this device is already here 
                }
            }
        }
        return -1; // this is not the device
    }
      
      public void save(Device device,User user){
          device.setUser(user);
          create(device);
      }

    public List<Device> findDeviceByUtilisateur(User user) {
        if (user == null || user.getLogin().equals("")) {
            return new ArrayList<>();
        } else if ( user.getDevices() != null ){
            String req = "SELECT d FROM Device d WHERE d.user.login='" + user.getLogin()+ "'";
            return em.createQuery(req).getResultList();
        }
        else 
            return new ArrayList<>();
    }
    
  


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeviceFacade() {
        super(Device.class);
    }
    
}
