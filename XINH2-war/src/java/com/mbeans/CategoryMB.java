/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.mbeans;


//import com.cusc.sessions.CategoriesFacadeLocal;
import com.cusc.entities.Categories;
import com.cusc.entities.Products;
import com.cusc.sessions.CategoriesFacadeLocal;
//import com.cusc.sessions.OrderDetailsFacadeLocal;
//import com.cusc.sessions.OrdersFacadeLocal;
//import java.util.HashMap;
//import javax.annotation.PostConstruct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
//import org.primefaces.model.charts.ChartData;
//import org.primefaces.model.charts.axes.cartesian.CartesianScales;
//import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
//import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
//import org.primefaces.model.charts.bar.BarChartDataSet;
//import org.primefaces.model.charts.bar.BarChartModel;
//import org.primefaces.model.charts.bar.BarChartOptions;
//import org.primefaces.model.charts.optionconfig.animation.Animation;
//import org.primefaces.model.charts.optionconfig.legend.Legend;
//import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
//import org.primefaces.model.charts.optionconfig.title.Title;

/**
 *
 * @author ngomi
 */
@Named(value = "categoryMB")
@RequestScoped
public class CategoryMB {

    

    @EJB
    private CategoriesFacadeLocal categoriesFacade;
    private Integer cateID;
    private String categoryName;
    private String description;
    private Categories categories;

    /**
     * Creates a new instance of CategoryMB
     */
    public CategoryMB() {
        categories = new Categories();
    }
    
    public List<Categories> showAllCategory() {
        return categoriesFacade.findAll();
    }
    
    public void createCategory() {
        categoriesFacade.create(categories);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("categoriesList.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CategoryMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String deleteCategory(Integer id) {
        categories = categoriesFacade.find(id);
        categoriesFacade.remove(categories);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("categoriesList.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CategoryMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String editCategories(Integer id) {
        categories = categoriesFacade.find(id);
        categoriesFacade.edit(categories);
        return "editCategory";
    }

    public void saveCategory() {
        categoriesFacade.edit(categories);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("categoriesList.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CategoryMB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String showAddForm() {
        categories = new Categories();
        return "addCategory";
    }
    
    public boolean validDelete(Integer id) {
        Categories c = categoriesFacade.find(id);
        List<Products> listProduct = new ArrayList<>();
        listProduct = (List<Products>)c.getProductsCollection();
        if (listProduct.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categories getCategory() {
        return categories;
    }

    public void setCategory(Categories category) {
        this.categories = category;
    }

    public CategoriesFacadeLocal getCategoriesFacade() {
        return categoriesFacade;
    }

    public void setCategoriesFacade(CategoriesFacadeLocal categoriesFacade) {
        this.categoriesFacade = categoriesFacade;
    }

    public Integer getCateID() {
        return cateID;
    }

    public void setCateID(Integer cateID) {
        this.cateID = cateID;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

}
