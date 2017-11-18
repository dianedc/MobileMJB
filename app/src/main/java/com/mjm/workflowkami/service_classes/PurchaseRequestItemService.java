package com.mjm.workflowkami.service_classes;

/**
 * Created by Jasper on 13 Sep 2017.
 */
import com.mjm.workflowkami.model_classes.ProjectClass;
import com.mjm.workflowkami.model_classes.PurchaseRequestItemClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PurchaseRequestItemService {

    //GET ALL PURCHASE REQUEST ITEMS
    @Headers("Content-Type: application/json")
    @GET("prequest/items")
    Call<List<PurchaseRequestItemClass>> getAllPReqItems();

    //GET ALL PURCHASE REQUEST ITEMS UNDER PURCHASE ID
    @GET("prequest/{prequestID}/item")
    Call<List<PurchaseRequestItemClass>> getItemByPReqId(@Path("prequestID") int preqID);

    //GET PURCHASE REQUEST ITEM BY ID
    @GET("prequest/{prequestID)/item/{preqitemID}")
    Call<ProjectClass> getPRequestById(@Path("prequestID") int preqID, @Path("preqitemID") int preqitemID);

    @POST("prequest/{prequestID}/item/add")
    Call<PurchaseRequestItemClass> addPReqItem(@Body PurchaseRequestItemClass preqItem);

    @POST("prequest/{prequestID}/item/update/{preqitemID}")
    Call<Void> editPReqItem(@Path("prequestID") int preqID, @Path("preqitemID") int preqitemID, @Body PurchaseRequestItemClass preqitem);
}
