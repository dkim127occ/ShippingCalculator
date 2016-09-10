package edu.orangecoastcollege.cs273.dkim127.shippingcalculator;

public class ShipItem
{
    private static final double BASE_COST = 3;
    private static final double SURCHARGE = 0.5;
    private static final double WEIGHT_THRESHOLD = 16;

    private double mWeight;
    private double mAddedCost;
    private double mTotalCost;

    /**
     * Returns the base cost for shipping this package.
     * @return the base cost
     */
    public double getBaseCost()
    {
        return BASE_COST;
    }

    /**
     * Returns the calculated additional cost for shipping this package.
     * @return the additional cost
     */
    public double getAddedCost()
    {
        return mAddedCost;
    }

    /**
     * Returns the calculated total cost for shipping this package.
     * @return the total cost
     */
    public double getTotalCost()
    {
        return mTotalCost;
    }

    /**
     * Sets the weight of the package.
     * @param weight the new weight to replace current weight
     */
    public void setWeight(double weight)
    {
        mWeight = weight;
        this.calculate();
    }

    /**
     * Private helper method that calculates all the costs associated with shipping this package.
     */
    private void calculate()
    {
        if (mWeight > 16)
        {
            mAddedCost = SURCHARGE * Math.ceil(mWeight / WEIGHT_THRESHOLD);
        }
        else
        {
            mAddedCost = 0;
        }

        mTotalCost = BASE_COST + mAddedCost;
    }
}
