package com.cqupt.pojo;

public class Brand {
    //id 主键
    private Integer id;
    //品牌名称
    private String brandName;
    //企业名称
    private String companyName;
    //排序字段
    private Integer ordered;
    //描述信息
    private String description;
    //状态：0禁用1启用
    private Integer status;


    public Brand() {
    }

    public Brand(Integer id, String brandName, String companyName, Integer ordered, String description, Integer status) {
        this.id = id;
        this.brandName = brandName;
        this.companyName = companyName;
        this.ordered = ordered;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    //
//    public String getStatusStr(){
//        if (this.status == 1){
//            return "启用";
//        }
//        return "禁用";
//    }

//    @Override
//    public String toString() {
//        return "Brand{" +
//                "id=" + id +
//                ", brandName='" + brandName + '\'' +
//                ", companyName='" + companyName + '\'' +
//                ", ordered=" + ordered +
//                ", description='" + description + '\'' +
//                ", status=" + status +
//                ", statusStr='" + statusStr + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", ordered=" + ordered +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}