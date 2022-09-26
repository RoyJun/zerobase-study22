package com.example.publicwifiinfo2022.controller;

import com.example.publicwifiinfo2022.apidto.MainDto;
import com.example.publicwifiinfo2022.apitest.ApiJsonServer;
import com.example.publicwifiinfo2022.apitest.TotalCnt;
import com.example.publicwifiinfo2022.dao.WifiDao;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.SQLException;

@WebServlet("/wifi-list.do")
public class WifiApiLoadController extends HttpServlet {

    private final ApiJsonServer apiJsonServer = new ApiJsonServer();
    private final WifiDao wifiDAO = new WifiDao();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        TotalCnt totalCnt = new TotalCnt();

        try {
            wifiDAO.deleteAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int num = totalCnt.getNum();
        int start = 0, end = 999;

        for (int i = 0; i < num; i++) {
            MainDto mainDTO = apiJsonServer.getwifiJson(start, end);

            try {
                wifiDAO.wifiInsert(mainDTO.getRowDtos());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            start += 1000;
            end += 1000;
        }

        req.setAttribute("totalCount", apiJsonServer.getwifiJson(0, 1).getTotalcount());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/wifiLoad.jsp");
        requestDispatcher.forward(req, res);
    }


}
