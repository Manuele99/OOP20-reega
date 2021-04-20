package reega.controllers;

import reega.data.ContractController;
import reega.data.models.Contract;
import reega.data.models.gson.NewContract;
import reega.users.User;
import reega.viewutils.AbstractViewModel;
import reega.viewutils.EventArgs;
import reega.viewutils.EventHandler;

import javax.inject.Inject;
import java.io.IOException;

public class ContractCreationViewModelImpl extends AbstractViewModel implements ContractCreationViewModel {

        private User user;
        private ContractController contractController;
        private EventHandler<Contract> contractEventHandler;

        @Inject
        public ContractCreationViewModelImpl(ContractController contractController) {
                this.contractController = contractController;
        }

        @Override
        public boolean registerContract(NewContract contract) {
                try {
                        Contract newContract = this.contractController.addContract(contract);
                        if (newContract != null) {
                                this.contractEventHandler.handle(new EventArgs<Contract>(newContract, this));
                        } else {
                                return false;
                        }
                } catch (IOException e) {
                        return false;
                }
                return true;
        }
        
        @Override
        public void setContractCreateEventHandler(EventHandler<Contract> eventHandler) {
                this.contractEventHandler = eventHandler;
        }

        @Override
        public void setUser(User newUser) {
                this.user = newUser;
        }

        @Override
        public User getUser() {
                return this.user;
        }
}