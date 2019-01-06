package com.example.deepaksharma.androidcode.global.api;


import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiList {

    // login
//    @FormUrlEncoded
//    @POST("/mobile/login")
//    Call<LoginModel> loginUser(@Field("username") String username, @Field("password") String password, @Field("device_id") String device_id);

//    // Forgot Password
//    @FormUrlEncoded
//    @POST("/mobile/forgot-password")
//    Call<ForgotPasswordModel> forgotPassword(@Field("mobile") String mobile);
//
//    // Registration
//    @Multipart
//    @POST("/mobile/register")
//    Call<RegisterModel> registerUser(@Part MultipartBody.Part transaction_image
//            , @Part MultipartBody.Part kyc_image
//            , @Part MultipartBody.Part aadhar_front_image
//            , @Part MultipartBody.Part aadhar_back_image
//            , @Part("fullname") RequestBody fullname
//            , @Part("gender") RequestBody gender
//            , @Part("mobile") RequestBody mobile
//            , @Part("email") RequestBody email
//            , @Part("refer_id") RequestBody refer_id
//            , @Part("checkposition") RequestBody checkposition
//            , @Part("transaction_id") RequestBody transaction_id
//    );
//
//    // Profile
//    @FormUrlEncoded
//    @POST("/mobile/profile")
//    Call<ProfileModel> profile(@Field("user_id") String user_id);
//
//
//    // Wallet Balance
//    @FormUrlEncoded
//    @POST("/mobile/wallet-balance")
//    Call<WallletModel> WalletBalance(@Field("user_id") String user_id);
//
//    // Wallet Withdraw
//    @FormUrlEncoded
//    @POST("/mobile/wallet-withdrawl")
//    Call<WithdrawModel> WalletWithdraw(@Field("user_id") String user_id, @Field("user_amount") String user_amount
//            , @Field("bank_name") String bank_name, @Field("acnumber") String acnumber, @Field("bankholder") String bankholder,
//                                       @Field("swiftcode") String swiftcode);
//
//    // Pair Details
//    @FormUrlEncoded
//    @POST("/mobile/binary-tree")
//    Call<PairModel> PairDetails(@Field("user_id") String user_id);
//
//    // Email Details
//    @FormUrlEncoded
//    @POST("mobile/send-contact")
//    Call<EmailModel> help(@Field("user_id") String user_id, @Field("contact_text") String contact_text);
//
//    // Contact Details
//    @FormUrlEncoded
//    @POST("/mobile/import-contact")
//    Call<com.money.instape.models.contacts.ContactModel> contactInfo(@Field("user_id") String user_id, @Field("phone_info") String phone_info);
//
//  /*  // Read Contact
//    @FormUrlEncoded
//    @POST("/profile/searchProfile")
//    Call<ResponseBody> readContacts(@Body ReadContacts readContacts);*/
//
//
//    @GET
//    Call<RechargeModel> getApiRecharge(@Url String url);
//
//
//    @FormUrlEncoded
//    @POST("/mobile/recharge-data")
//    Call<SavePaymentDetails> savePaymentDetails(@Field("user_id") String userId,
//                                                @Field("user_amount") String sendNumber,
//                                                @Field("send_number") String strAmount,
//                                                @Field("send_operator") String send_operator,
//                                                @Field("unique_id") String strOperatorId,
//                                                @Field("recharge_type") String strUniqueNo
//    );
//
//    @FormUrlEncoded
//    @POST("/mobile/change-password")
//    Call<ChangePasswordModel> changePassword(@Field("uname") String uname,
//                                             @Field("password") String password
//
//    );









    /*@Multipart
    @POST("/mobileUser/userRegister")
    Call<JsonElement> registerUser(@Part("file_imagedata\"; filename=\".jpg\" ") RequestBody file, @Part("firstname") RequestBody firstname
            , @Part("mobile") RequestBody mobile
            , @Part("email") RequestBody email
            , @Part("gender") RequestBody gender
            , @Part("dob") RequestBody dob
            , @Part("blood_group") RequestBody blood_group
            , @Part("registerPassword") RequestBody registerPassword
            , @Part("device_id") RequestBody device_id
            , @Part("registration_id") RequestBody reg_id
            , @Part("device_type") RequestBody device_type
            , @Part("address1") RequestBody address1
            , @Part("address2") RequestBody address2
            , @Part("city") RequestBody city
            , @Part("state") RequestBody state
            , @Part("locality") RequestBody locality
            , @Part("locality_lat") RequestBody locality_lat
            , @Part("locality_long") RequestBody locality_long
            , @Part("sos_number") RequestBody sos_number

    );*/

    /* OkHttpClient okHttpClient = new OkHttpClient.Builder() // trying to exceed timeout limit
             .connectTimeout(1, TimeUnit.MINUTES)
             .readTimeout(30, TimeUnit.SECONDS)
             .writeTimeout(15, TimeUnit.SECONDS)
             .build();
       .client(okHttpClient)*/
//    Gson gson = new GsonBuilder()
//            .setLenient()
//            .create();
//    ApiList retrofit = new Retrofit.Builder()
//            .baseUrl(ApiConstants.root)
//            .addConverterFactory(new NullOnEmptyConverterFactory())
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build().create(ApiList.class);

    //    @FormUrlEncoded
//    @POST(ApiConstants.LOGINLINK)
//    Call<JsonElement> loginApi(@Field("username") String username, @Field("password") String password);

//    @POST(ApiConstants.LOGIN)
//    Call<LoginResp> androidLoginApi(@Body LoginReq loginreq);


}
