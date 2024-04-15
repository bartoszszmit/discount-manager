
import java.util.Set;

public class Combination {
    private Set<CombinationSet> combinationSetList;
    private Double sum;
    private Double rabat;
    private Double benefit;

    public Combination(Set<CombinationSet> combinationSetList) {
        this.setCombinationSetList(combinationSetList);
        this.setSum(combinationSetList.stream().map(x->x.getSum()).reduce(0.0, Double::sum));
        this.setRabat(combinationSetList.stream().map(x->x.getRabat()).reduce(0.0, Double::sum));
        this.setBenefit(rabat/sum);
    }

    @Override
    public String toString() {
        return "Combination{" +
                " sum = " + getSum() +
                String.format(" rabat = %.2f", rabat) +
                String.format(" benefit = %.3f", benefit) +
                " combinationSetList=" + getCombinationSetList() +
                '}';
    }

    public Set<CombinationSet> getCombinationSetList() {
        return combinationSetList;
    }

    public void setCombinationSetList(Set<CombinationSet> combinationSetList) {
        this.combinationSetList = combinationSetList;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getRabat() {
        return rabat;
    }

    public void setRabat(Double rabat) {
        this.rabat = rabat;
    }

    public Double getBenefit() {
        return benefit;
    }

    public void setBenefit(Double benefit) {
        this.benefit = benefit;
    }
}
