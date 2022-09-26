package com.example.publicwifiinfo2022.controller;

import com.example.publicwifiinfo2022.dao.HistoryDao;
import com.example.publicwifiinfo2022.vo.HistoryVo;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/history-list.do")
public class GetHistoryListController extends HttpServlet {
    private final HistoryDao historyDao;

    public GetHistoryListController() {
        this.historyDao = new HistoryDao();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<HistoryVo> list = historyDao.selectHistoryList();

        req.setAttribute("list", list);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/history.jsp");
        requestDispatcher.forward(req, res);
    }
}
