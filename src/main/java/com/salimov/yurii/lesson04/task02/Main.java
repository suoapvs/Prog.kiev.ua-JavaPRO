package com.salimov.yurii.lesson04.task02;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 4.2 Создать проект «Анкета». Сделать возможность ввода пользователем его имени,
 фамилии, возраста и ответов на 2-3 вопроса. Данные должны отправляться на сервер,
 который в ответ должен вернуть статистику по ответам в виде HTML документа.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class Main extends HttpServlet {

    private static final String TEMPLATE = "<!DOCTYPE html>" + "\n" +
            "<html>" + "\n" +
            "<head>" + "\n" +
            "    <title>Results</title>" + "\n" +
            "</head>" + "\n" +
            "<body>" + "\n" +
            "<h2>%s</h2>" + "\n" +
            "</body>" + "\n" +
            "</html>";

    private static int[] results = new int[4];

    public void doPost(
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws IOException {
        final String question1 = request.getParameter("question1");
        final String question2 = request.getParameter("question2");
        final int indexJava = "yes".equals(question1) ? 0 : "no".equals(question1) ? 1 : -1;
        final int indexC = "yes".equals(question2) ? 2 : "no".equals(question2) ? 3 : -1;
        if (indexJava != -1) {
            results[indexJava]++;
        }
        if (indexC != -1) {
            results[indexC]++;
        }
        answer(response);
    }

    public void doGet(
            final HttpServletRequest request,
            final HttpServletResponse response
    ) throws IOException {
        results = new int[4];
        answer(response);
    }

    private void answer(final HttpServletResponse response) throws IOException {
        final String res = "Do you like Java?: yes = " + results[0] + ", no = " + results[1] + "</br>" + "\n"
                + "Do you like C++?: yes = " + results[2] + ", no = " + results[3] + "</br>";
        response.setContentType("text/html");
        try(PrintWriter writer = response.getWriter()) {
            writer.println(String.format(TEMPLATE, res));
        }
    }
}
