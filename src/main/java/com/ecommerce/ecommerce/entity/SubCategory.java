package com.ecommerce.ecommerce.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "subcategories",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"slug", "category_id"})
        }
)
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2048)
    private String icon;

    @Column(length = 100, nullable = false)
    private String slug;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "subCategory", cascade = CascadeType.ALL)
    private List<Product> products;

    public SubCategory(){
    }

    public SubCategory(Long id, String name, String icon, String slug, Category category, List<Product> products){
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.slug = slug;
        this.category = category;
        this.products = products;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getIcon(){
        return icon;
    }

    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getSlug(){
        return slug;
    }

    public void setSlug(String slug){
        this.slug = slug;
    }

    public Category getCategory(){
        return category;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public List<Product> getProducts(){
        return products;
    }

    public void setProducts(List<Product> products){
        this.products = products;
    }

}
