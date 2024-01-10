/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;

import com.cusc.customssbean.CustomCartLocal;
import com.cusc.entities.Customers;
import com.cusc.entities.Orders;
import com.cusc.entities.Products;
import com.cusc.sessions.CustomersFacadeLocal;
import com.cusc.sessions.OrdersFacadeLocal;
import com.cusc.sessions.ProductsFacadeLocal;
import com.modals.RevenueByMonth;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;

/**
 *
 * @author ngomi
 */
@Named
@RequestScoped
public class ChartJsView implements Serializable {

    @EJB
    private ProductsFacadeLocal productsFacade;

    @EJB
    private CustomCartLocal customCart;

    private BarChartModel barModel;
    private BarChartModel revenueProduct;

    private BarChartModel revenueByMonth;

    private BarChartModel revenueAll;

    private int eventClick;

    private String dateStart;

    private String dateEnd;

    private double totalPrice = 0;

    public BarChartModel getRevenueByMonth() {
        return revenueByMonth;
    }

    public void setRevenueByMonth(BarChartModel revenueByMonth) {
        this.revenueByMonth = revenueByMonth;
    }

    @PostConstruct
    public void init() {

        revenuProductChart();
        eventClick = 0;
    }

    public String loadChart() {
        System.out.println("--->1" + dateStart + " --->1" + dateEnd);
        try {
            System.out.println("--->2" + dateStart + " --->2" + dateEnd);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Date startConvert = formatter.parse(dateStart);
            Date endConvert = formatter.parse(dateEnd);
            int quantityProductSold = getProductSold();
            System.out.println("-1----" + totalPrice);
           
            revenuStatistic(totalPrice);
        } catch (Exception e) {
            System.out.println("---=" + e);
        }
        eventClick++;
        return "statistic";
    }

    private double getRevenu() {

        return 0;

    }

    private int getProductSold() {
        int quantitySoldProduct = 0;
        List<Products> listPro = productsFacade.findAll();
        for (Products products : listPro) {
            if (!products.getOrderDetailsCollection().isEmpty()) {
                quantitySoldProduct += products.getOrderDetailsCollection().size();
            }
        }
        return quantitySoldProduct;
    }


    public void createBarModel(int quantityProductSold, int quantityPus, double price) {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        System.out.println("-------3-" + price);

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Chart");//sua ten o day

        List<Number> values = new ArrayList<>();
        values.add(price);
        values.add(quantityProductSold);
        values.add(quantityPus);

        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");

        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");

        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Revenue statistics");
        labels.add("Statistics of the number of products sold");
        labels.add("Statistics of the number of customers purchased the product");

        data.setLabels(labels);
        barModel.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Revenu Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        barModel.setOptions(options);
    }

    private List<RevenueByMonth> getData() {
        List<RevenueByMonth> list = new ArrayList<>();
        list.add(new RevenueByMonth(1, 0));
        list.add(new RevenueByMonth(2, 0));
        list.add(new RevenueByMonth(3, 0));
        list.add(new RevenueByMonth(4, 0));
        list.add(new RevenueByMonth(5, 0));
        list.add(new RevenueByMonth(6, 0));
        list.add(new RevenueByMonth(7, 0));
        list.add(new RevenueByMonth(8, 0));
        list.add(new RevenueByMonth(9, 0));
        list.add(new RevenueByMonth(10, 0));
        list.add(new RevenueByMonth(11, 0));
        list.add(new RevenueByMonth(12, 0));
        return list;
    }

    

    public void revenuProductChart() {
        List<Products> listProduct = productsFacade.findAll();
        revenueProduct = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Quantity");//sua ten o day

        List<Number> values = new ArrayList<>();
        for (Products products : listProduct) {
            if (products != null) {
                values.add(products.getQuantity());
            } else {
                values.add(0);
            }
        }

        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        for (int i = 0; i < productsFacade.findAll().size(); i++) {
            bgColor.add("rgba(255, 99, 132, 0.2)");
        }

        barDataSet.setBackgroundColor(bgColor);
        List<String> borderColor = new ArrayList<>();
        for (int i = 0; i < productsFacade.findAll().size(); i++) {
            borderColor.add("rgb(255, 99, 132)");
        }

        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        for (Products products : listProduct) {
            labels.add(products.getProductName());
        }
        data.setLabels(labels);
        revenueProduct.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Revenu Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(10000);
        options.setAnimation(animation);

        revenueProduct.setOptions(options);
    }
    public void revenuStatistic(double price) {
        revenueAll = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Price");//sua ten o day

        List<Number> values = new ArrayList<>();
        values.add(price);

        barDataSet.setData(values);

        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 205, 86, 0.2)");

        barDataSet.setBackgroundColor(bgColor);

        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 205, 86)");

        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        List<String> labels = new ArrayList<>();
        labels.add("Revenue statistics");

        data.setLabels(labels);
        revenueAll.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        linearAxes.setBeginAtZero(true);
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);

        Title title = new Title();
        title.setDisplay(true);
        title.setText("Revenu Chart");
        options.setTitle(title);

        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        // disable animation
        Animation animation = new Animation();
        animation.setDuration(0);
        options.setAnimation(animation);

        revenueAll.setOptions(options);
    }

    public void itemSelect(ItemSelectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
                "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public int getEventClick() {
        return eventClick;
    }

    public void setEventClick(int eventClick) {
        this.eventClick = eventClick;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BarChartModel getRevenueProduct() {
        return revenueProduct;
    }

    public void setRevenueProduct(BarChartModel revenueProduct) {
        this.revenueProduct = revenueProduct;
    }

    public BarChartModel getRevenueAll() {
        return revenueAll;
    }

    public void setRevenueAll(BarChartModel revenueAll) {
        this.revenueAll = revenueAll;
    }
}
