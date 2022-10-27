package Enum;

public enum ProductBuyEnum {
    SearchBarInputBox("nav-search-field "),
    SearchButton("nav-right"),
    addToCartButton("submit.add-to-cart");
    private String name;

    private ProductBuyEnum(String name) {
        this.name = name;

    }

    public String getResourcesName() {
        return name;
    }
}

