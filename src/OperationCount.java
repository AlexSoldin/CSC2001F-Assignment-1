/*
This class stores the summarised version of the best/worst cases for the data collected in PowerUserApp where efficiencies are
compared.
A parameterised constructor has been defined
All setters and getters are declared
 */
public class OperationCount {

    private int numberOfElements, btBestCase, btWorstCase, arrBestCase, arrWorstCase;
    private double btAv, arrAv;

    public OperationCount(int elements) {
        this.numberOfElements = elements;
        btBestCase = 0;
        btWorstCase = 0;
        arrBestCase = 0;
        arrWorstCase = 0;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public int getBtBestCase() {
        return btBestCase;
    }

    public int getBtWorstCase() {
        return btWorstCase;
    }

    public int getArrBestCase() {
        return arrBestCase;
    }

    public int getArrWorstCase() {
        return arrWorstCase;
    }

    public double getBtAv() {
        return btAv;
    }

    public double getArrAv() {
        return arrAv;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public void setBtBestCase(int btBestCase) {
        this.btBestCase = btBestCase;
    }

    public void setBtWorstCase(int btWorstCase) {
        this.btWorstCase = btWorstCase;
    }

    public void setArrBestCase(int arrBestCase) {
        this.arrBestCase = arrBestCase;
    }

    public void setArrWorstCase(int arrWorstCase) {
        this.arrWorstCase = arrWorstCase;
    }

    public void setBtAv(double btAv) {
        this.btAv = btAv;
    }

    public void setArrAv(double arrAv) {
        this.arrAv = arrAv;
    }

    //This method prepares all the variables in the necessary format to be written to a .CSV file
    //i.e. separated by a comma
    public String toFile(){
        return numberOfElements + "," + btBestCase + "," + btWorstCase + "," + arrBestCase + "," + arrWorstCase + "," + btAv + "," + arrAv + "\n";
    }

    //This method prints out all the data stored in OperationCount separated by a single space
    public String toString(){
        return numberOfElements+" "+ btBestCase+" "+btWorstCase+" "+arrBestCase+" "+arrWorstCase+" "+btAv+" "+arrAv+"\n";
    }
}
