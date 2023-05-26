package com.example.ratatouille.Network;

public interface RemoteSource {
    public void enqueueCallIngredient(NetworkDelegate networkDelegate,String search);
    public void enqueueCallCategories(NetworkDelegate networkDelegate,String search);
    public void enqueueCallCountry(NetworkDelegate networkDelegate,String search);
    public void enqueueCallId(NetworkDelegate networkDelegate,String search);
    public void enqueueCallName(NetworkDelegate networkDelegate,String search);
    public void enqueueRandomMeal(NetworkDelegate networkDelegate);
    public void enqueueGetIngredient(NetworkDelegate networkDelegate);
    public void enqueueGetCategory(NetworkDelegate networkDelegate);
    public void enqueueGetCountry(NetworkDelegate networkDelegate);

}
