package com.example.OrderProject_MVC.controller.ThymeLeafPresenter;

import org.springframework.ui.Model;

public interface IPresenter {
    public void prepareOrdersPage(String orderStatus,
                                  String email,
                                  String date,
                                  Model model);

    public void prepareOrderDetails(Long id, Model model);

}
