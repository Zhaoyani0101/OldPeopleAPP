package com.zhaoyani.myapp.bean;

public class Recipe {
    private int _id;
    private int recipe_img;
    private String recipe_title;
    private String createTime;
    private String creator;

    public Recipe() {
    }

    public Recipe(int _id, int recipe_img, String recipe_title, String createTime, String creator) {
        this._id = _id;
        this.recipe_img = recipe_img;
        this.recipe_title = recipe_title;
        this.createTime = createTime;
        this.creator = creator;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getRecipe_img() {
        return recipe_img;
    }

    public void setRecipe_img(int recipe_img) {
        this.recipe_img = recipe_img;
    }

    public String getRecipe_title() {
        return recipe_title;
    }

    public void setRecipe_title(String recipe_title) {
        this.recipe_title = recipe_title;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
