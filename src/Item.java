
public class Item {
    private Integer id;
    private String name;
    private Double price;
    private boolean multirabat;
    private boolean secondThirty;
    private boolean secondThirtyThree;
    private Double customDiscountValue;

    public Item(Integer id, String name, Double price, boolean multirabat, boolean secondThirty, boolean secondThirtyThree, Double customDiscountValue) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.multirabat = multirabat;
        this.secondThirty = secondThirty;
        this.secondThirtyThree = secondThirtyThree;
        this.customDiscountValue = customDiscountValue;
    }

    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isMultirabat() {
        return multirabat;
    }

    public void setMultirabat(boolean multirabat) {
        this.multirabat = multirabat;
    }

    public boolean isSecondThirty() {
        return secondThirty;
    }

    public void setSecondThirty(boolean secondThirty) {
        this.secondThirty = secondThirty;
    }

    public boolean isSecondThirtyThree() {
        return secondThirtyThree;
    }

    public void setSecondThirtyThree(boolean secondThirtyThree) {
        this.secondThirtyThree = secondThirtyThree;
    }

    @Override
    public String toString() {
        return  name + " " + price +
                (multirabat ? " MR" : "") +
                (secondThirty ? " 30%" : "") +
                (secondThirtyThree ? " 33%" : "") +
                (customDiscountValue != null ? " -" + customDiscountValue : "");
    }

    public Double getCustomDiscountValue() {
        return customDiscountValue;
    }

    public void setCustomDiscountValue(Double customDiscountValue) {
        this.customDiscountValue = customDiscountValue;
    }
}
