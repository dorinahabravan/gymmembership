package com.habravanEnterprise.fitnessForLife.gui;

import com.habravanEnterprise.fitnessForLife.models.Client;

public interface FormIntf {

    void clearForm();

    void fillForm(Client client);

    Client readForm();

}
