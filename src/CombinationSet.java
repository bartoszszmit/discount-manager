import java.util.*;
import java.util.stream.Collectors;

public class CombinationSet {
    private Set<Item> combination;
    private Double sum;
    private Double rabat;
    private Double benefit;
    private String discountType;

    public CombinationSet(HashSet<Item> combination) {
        this.combination = combination;
        this.sum = combination.stream().map(Item::getPrice).reduce(0.0, Double::sum);
        this.rabat = establishDiscount();
        //this.benefit = rabat/sum;
    }

    private Double establishDiscount() {
        List<Item> mr = this.combination.stream().filter(x-> x.isMultirabat()).collect(Collectors.toList());
        List<Item> secondThirty = this.combination.stream().filter(x-> x.isSecondThirty()).collect(Collectors.toList());
        List<Item> secondThirtyThree = this.combination.stream().filter(x-> x.isSecondThirtyThree()).collect(Collectors.toList());

        double theBest = 0;

        if(this.combination.size() == 1){
            var item = this.combination.stream().map(x->x.getCustomDiscountValue()).filter(Objects::nonNull).findAny();
            if(item.isPresent()){
                theBest = item.get();
                discountType = "Custom Discount";
            }
        }
        if(mr.size() >= 2){
            double res = calculateDiscountMr(mr);
            if(res > theBest) {
                theBest = res;
                discountType = "MR";
            }
        }
        if( secondThirty.size() >= 2)
        {
            double res = calculateDiscountSecondThirty(secondThirty);
            if(res > theBest) {
                theBest = res;
                discountType = "30%";
            }
        }
        if( secondThirtyThree.size() >= 2)
        {
            double res = calculateDiscountSecondThirtyThree(secondThirtyThree);
            if(res > theBest) {
                theBest = res;
                discountType = "33%";
            }
        }
        return theBest;
    }
    private double calculateDiscountSecondThirty(List<Item> secondThirty) {
        var sortedDescending = secondThirty.stream().sorted( Comparator.comparingDouble(Item::getPrice).reversed()).collect(Collectors.toList());
        return sortedDescending.get(1).getPrice()*0.3;
    }
    private double calculateDiscountSecondThirtyThree(List<Item> secondThirtyThree) {
        var sortedDescending = secondThirtyThree.stream().sorted( Comparator.comparingDouble(Item::getPrice).reversed()).collect(Collectors.toList());
        return sortedDescending.get(1).getPrice()*0.33;
    }

    private double calculateDiscountMr(List<Item> mr) {
        var sortedDescending = mr.stream().sorted( Comparator.comparingDouble(Item::getPrice).reversed()).collect(Collectors.toList());
        Double lastPrice = 0.0;
        switch( mr.size() )
        {
            case 2:
                lastPrice = sortedDescending.get(1).getPrice() * (0.3);
                break;
            case 3:
                lastPrice = sortedDescending.get(2).getPrice() * (0.55);
                break;
            case 4:
                lastPrice = sortedDescending.get(3).getPrice() * (0.8);
                break;
            case 5:
                lastPrice = sortedDescending.get(4).getPrice() - 1.0;
                break;
        }
        return lastPrice;
    }

    public Set<Item> getCombination() {
        return combination;
    }

    public void setCombination(Set<Item> combination) {
        this.combination = combination;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getRabat() {
        return rabat;
    }

    public void setRabat(double rabat) {
        this.rabat = rabat;
    }

    public double getBenefit() {
        return benefit;
    }

    public void setBenefit(double benefit) {
        this.benefit = benefit;
    }

    @Override
    public String toString() {
        return "CombinationSet{" +
            " sum = " + sum +
            String.format(" rabat = %.2f", rabat) +
            //" benefit = " + benefit +
            " discountType = " + discountType +
            " combination = " + combination +
            '}';
    }
}
