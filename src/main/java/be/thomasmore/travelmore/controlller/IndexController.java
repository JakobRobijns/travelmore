package be.thomasmore.travelmore.controlller;


import be.thomasmore.travelmore.domain.Transportmiddel;
import be.thomasmore.travelmore.service.TransportmiddelService;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@ManagedBean
@ViewScoped
public class IndexController {

    public List<Transportmiddel> transportmiddelen;


    @Inject
    private TransportmiddelService transportmiddelService;


    public void init() {
        this.transportmiddelen = this.getTransportmiddelen();
    }

    public List<Transportmiddel> getTransportmiddelen(){
        return this.transportmiddelService.findAllTransportmiddelen();
    }

}
