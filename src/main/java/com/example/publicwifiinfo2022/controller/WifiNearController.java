package com.example.publicwifiinfo2022.controller;

import com.example.publicwifiinfo2022.dao.HistoryDao;
import com.example.publicwifiinfo2022.dao.WifiDao;
import com.example.publicwifiinfo2022.vo.WifiVo;

import lombok.SneakyThrows;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/wifiNear.do")
public class WifiNearController extends HttpServlet {

    WifiDao wifiDao = new WifiDao();
    HistoryDao historyDao = new HistoryDao();

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Double lat = Double.valueOf(request.getParameter("lat"));
        Double lnt = Double.valueOf(request.getParameter("lnt"));

        request.setAttribute("lat", lat);
        request.setAttribute("lnt", lnt);

        List<WifiVo> list = wifiDao.selectSearchWifiList(lat, lnt);
        historyDao.insertHistory(lat, lnt);

        request.setAttribute("list", list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");
        requestDispatcher.forward(request, response);

    }
}
