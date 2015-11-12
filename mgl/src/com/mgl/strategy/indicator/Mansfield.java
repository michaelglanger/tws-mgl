package com.mgl.strategy.indicator;

import com.mgl.entities.*;
import com.mgl.strategy.indicator.exceptions.MansfieldException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Michael G. Langer on 5/27/2015.
 */
public class Mansfield {

    private double calculateRP(BarEntity stkBar, BarEntity idxBar) {
        if (stkBar == null || idxBar == null) {
            return 0;
        }
        return stkBar.getBclose().doubleValue() / idxBar.getBclose().doubleValue() * 100;
    }

    public double execute(ContractEntity contract, int mvaDays, String indexName) throws MansfieldException {
        ControllerFactory controllerFactory = new ControllerFactory();
        ContractJpaController controller = controllerFactory.getContractJpaController();
        ContractEntity contractEntity = controller.findContract(indexName);
        if (contractEntity == null) {
            throw new MansfieldException("Index doesn't exist: " + indexName);
        }
        Collection<BarEntity> barsIdx = contractEntity.getBarCollection();
        return this.execute(new ArrayList<>(contract.getBarCollection()), mvaDays, new ArrayList<>(barsIdx));
    }
    
    public double execute(List<BarEntity> bars, int mvaDays, List<BarEntity> barsIdx) throws MansfieldException {
        ControllerFactory controllerFactory = new ControllerFactory();

        ContractJpaController controller = controllerFactory.getContractJpaController();
//        ContractEntity contractEntity = controller.findContract(indexName);
//        if (contractEntity == null) {
//            throw new MansfieldException("Index doesn't exist: " + indexName);
//        }
//        Collection<BarEntity> bars = contract.getBarCollection();
        
        
//        Collection<BarEntity> barsIdx = contractEntity.getBarCollection();

        List<BarEntity> brs = null;
        List<BarEntity> brsIdx = null;
        if ( bars.size() > mvaDays && barsIdx.size() > mvaDays) {
            brs = new ArrayList(bars).subList(bars.size()-mvaDays, bars.size());
            brsIdx = new ArrayList(barsIdx).subList(barsIdx.size()-mvaDays, barsIdx.size());
        } else {
//           throw new MansfieldException(bars.size() + " " + barsIdx.size());
        }

        double dd = 0;
        if (brs != null && brsIdx != null && brs.size() == brsIdx.size()) {
            for (int i = 0; i < mvaDays; i++) {
                dd += calculateRP(brs.get(i), brsIdx.get(i));
            }
            dd = dd / mvaDays;
        } else {
//            throw new MansfieldException(brs + " " + brsIdx);
        }

        if (dd == 0) {
            return 0;
//            throw new MansfieldException("Division by 0.");
        }

        BarJpaController barJpaController = controllerFactory.getBarJpaController();
        BarEntity stkBar = bars.get(bars.size()-1); //barJpaController.findLastBar(contract);
        BarEntity idxBar = barsIdx.get(barsIdx.size()-1); //barJpaController.findLastBar(contractEntity);
        double pr = calculateRP(stkBar, idxBar);
        return ((pr / dd) - 1) * 100;
    }
}
