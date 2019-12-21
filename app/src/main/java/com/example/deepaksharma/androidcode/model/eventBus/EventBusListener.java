package com.example.deepaksharma.androidcode.model.eventBus;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Deepak Sharma on 18/1/19.
 */
public class EventBusListener {


    /**
     * status_code : 1
     * message : Vehicle Type list
     * vehicleTypeList : [{"id":"4","vehicle_type_name":"Commercial Vehicle","vehicleTypeImage":[{"image_type_id":"60","image_type_name":"Glass Image"},{"image_type_id":"59","image_type_name":"Seat Image"},{"image_type_id":"58","image_type_name":"Dashboard Image"},{"image_type_id":"57","image_type_name":"Close Damage Photo3"},{"image_type_id":"56","image_type_name":"Close Damage Photo2"},{"image_type_id":"55","image_type_name":"Close Damage Photo1"},{"image_type_id":"54","image_type_name":"Under Carriage"},{"image_type_id":"53","image_type_name":"Bottom Open "},{"image_type_id":"52","image_type_name":"Diggi Open"},{"image_type_id":"51","image_type_name":"RC Photo2"},{"image_type_id":"50","image_type_name":"RC Photo1"},{"image_type_id":"49","image_type_name":"Meter Reading"},{"image_type_id":"48","image_type_name":"Engine Photo"},{"image_type_id":"47","image_type_name":"Rear Side"},{"image_type_id":"46","image_type_name":"Right Side"},{"image_type_id":"45","image_type_name":"Left Side"},{"image_type_id":"44","image_type_name":"Front Side"},{"image_type_id":"43","image_type_name":"Chasis Number"}]},{"id":"3","vehicle_type_name":"Tractor / Machinery","vehicleTypeImage":[{"image_type_id":"42","image_type_name":"Close Damage Photo3"},{"image_type_id":"41","image_type_name":"Close Damage Photo2"},{"image_type_id":"40","image_type_name":"Close Damage Photo1"},{"image_type_id":"39","image_type_name":"Under Carriage"},{"image_type_id":"38","image_type_name":"Bottom Open "},{"image_type_id":"37","image_type_name":"Diggi Open"},{"image_type_id":"36","image_type_name":"RC Photo2"},{"image_type_id":"35","image_type_name":"RC Photo1"},{"image_type_id":"34","image_type_name":"Meter Reading"},{"image_type_id":"33","image_type_name":"Engine Photo"},{"image_type_id":"32","image_type_name":"Rear Side"},{"image_type_id":"31","image_type_name":"Right Side"},{"image_type_id":"30","image_type_name":"Left Side"},{"image_type_id":"29","image_type_name":"Front Side"},{"image_type_id":"28","image_type_name":"Chasis Number"}]},{"id":"2","vehicle_type_name":"Car","vehicleTypeImage":[{"image_type_id":"27","image_type_name":"Glass Image"},{"image_type_id":"26","image_type_name":"Seat Image"},{"image_type_id":"25","image_type_name":"Dashboard Image"},{"image_type_id":"24","image_type_name":"Close Damage Photo3"},{"image_type_id":"23","image_type_name":"Close Damage Photo2"},{"image_type_id":"22","image_type_name":"Close Damage Photo1"},{"image_type_id":"21","image_type_name":"Under Carriage"},{"image_type_id":"20","image_type_name":"Bottom Open "},{"image_type_id":"19","image_type_name":"Diggi Open"},{"image_type_id":"18","image_type_name":"RC Photo2"},{"image_type_id":"17","image_type_name":"RC Photo1"},{"image_type_id":"16","image_type_name":"Meter Reading"},{"image_type_id":"15","image_type_name":"Engine Photo"},{"image_type_id":"14","image_type_name":"Rear Side"},{"image_type_id":"13","image_type_name":"Right Side"},{"image_type_id":"12","image_type_name":"Left Side"},{"image_type_id":"11","image_type_name":"Front Side"},{"image_type_id":"10","image_type_name":"Chasis Number"}]},{"id":"1","vehicle_type_name":"Two Vehicle","vehicleTypeImage":[{"image_type_id":"9","image_type_name":"RC Photo2"},{"image_type_id":"8","image_type_name":"RC Photo1"},{"image_type_id":"7","image_type_name":"Meter Reading"},{"image_type_id":"6","image_type_name":"Engine Photo"},{"image_type_id":"5","image_type_name":"Rear Side"},{"image_type_id":"4","image_type_name":"Right Side"},{"image_type_id":"3","image_type_name":"Left Side"},{"image_type_id":"2","image_type_name":"Front Side"},{"image_type_id":"1","image_type_name":"Chasis Number"}]}]
     * user_status : 1
     */

    private String status_code;
    private String message;
    private String user_status;
    private MutableLiveData<ArrayList<String>>user_statusss = new MutableLiveData<>();

    private List<VehicleTypeListBean> vehicleTypeList;

//    public String getStatus_code() {
//        user_statusss.observe(this, new Observer<ArrayList<String>>() {
//            @Override
//            public void onChanged(ArrayList<String> strings) {
//
//            }
//        });
//        return status_code;
//    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public List<VehicleTypeListBean> getVehicleTypeList() {
        return vehicleTypeList;
    }

    public void setVehicleTypeList(List<VehicleTypeListBean> vehicleTypeList) {
        this.vehicleTypeList = vehicleTypeList;
    }

    public static class VehicleTypeListBean {
        /**
         * id : 4
         * vehicle_type_name : Commercial Vehicle
         * vehicleTypeImage : [{"image_type_id":"60","image_type_name":"Glass Image"},{"image_type_id":"59","image_type_name":"Seat Image"},{"image_type_id":"58","image_type_name":"Dashboard Image"},{"image_type_id":"57","image_type_name":"Close Damage Photo3"},{"image_type_id":"56","image_type_name":"Close Damage Photo2"},{"image_type_id":"55","image_type_name":"Close Damage Photo1"},{"image_type_id":"54","image_type_name":"Under Carriage"},{"image_type_id":"53","image_type_name":"Bottom Open "},{"image_type_id":"52","image_type_name":"Diggi Open"},{"image_type_id":"51","image_type_name":"RC Photo2"},{"image_type_id":"50","image_type_name":"RC Photo1"},{"image_type_id":"49","image_type_name":"Meter Reading"},{"image_type_id":"48","image_type_name":"Engine Photo"},{"image_type_id":"47","image_type_name":"Rear Side"},{"image_type_id":"46","image_type_name":"Right Side"},{"image_type_id":"45","image_type_name":"Left Side"},{"image_type_id":"44","image_type_name":"Front Side"},{"image_type_id":"43","image_type_name":"Chasis Number"}]
         */

        private String id;
        private String vehicle_type_name;
        private List<VehicleTypeImageBean> vehicleTypeImage;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVehicle_type_name() {
            return vehicle_type_name;
        }

        public void setVehicle_type_name(String vehicle_type_name) {
            this.vehicle_type_name = vehicle_type_name;
        }

        public List<VehicleTypeImageBean> getVehicleTypeImage() {
            return vehicleTypeImage;
        }

        public void setVehicleTypeImage(List<VehicleTypeImageBean> vehicleTypeImage) {
            this.vehicleTypeImage = vehicleTypeImage;
        }

        public static class VehicleTypeImageBean {
            /**
             * image_type_id : 60
             * image_type_name : Glass Image
             */

            private String image_type_id;
            private String image_type_name;

            public String getImage_type_id() {
                return image_type_id;
            }

            public void setImage_type_id(String image_type_id) {
                this.image_type_id = image_type_id;
            }

            public String getImage_type_name() {
                return image_type_name;
            }

            public void setImage_type_name(String image_type_name) {
                this.image_type_name = image_type_name;
            }
        }
    }
}
