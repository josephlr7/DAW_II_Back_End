package cibertec.pe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String shortName;
    private String category;
    private String categoryLabel;
    private String brand; // Marca oficial
    @Column(nullable = false)
    private Double price;
    private Double originalPrice;
    @Column(columnDefinition = "TEXT")
    private String image;
    private String tag;
    private String tagColor;
    private Integer stock;
    private String sku;
    @Column(columnDefinition = "TEXT")
    private String description;
    private Double rating = 5.0;
    private Integer reviewsCount = 0;
    private Boolean active = true;
    public Product() {
    }
    public Product(Long id, String name, String shortName, String category, String categoryLabel, String brand,
                   Double price, Double originalPrice, String image, String tag, String tagColor,
                   Integer stock, String sku, String description, Double rating, Integer reviewsCount, Boolean active) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.category = category;
        this.categoryLabel = categoryLabel;
        this.brand = brand;
        this.price = price;
        this.originalPrice = originalPrice;
        this.image = image;
        this.tag = tag;
        this.tagColor = tagColor;
        this.stock = stock;
        this.sku = sku;
        this.description = description;
        this.rating = rating;
        this.reviewsCount = reviewsCount;
        this.active = active;
    }
    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getShortName() { return shortName; }
    public void setShortName(String shortName) { this.shortName = shortName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getCategoryLabel() { return categoryLabel; }
    public void setCategoryLabel(String categoryLabel) { this.categoryLabel = categoryLabel; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Double getOriginalPrice() { return originalPrice; }
    public void setOriginalPrice(Double originalPrice) { this.originalPrice = originalPrice; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }
    public String getTagColor() { return tagColor; }
    public void setTagColor(String tagColor) { this.tagColor = tagColor; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    public Integer getReviewsCount() { return reviewsCount; }
    public void setReviewsCount(Integer reviewsCount) { this.reviewsCount = reviewsCount; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
