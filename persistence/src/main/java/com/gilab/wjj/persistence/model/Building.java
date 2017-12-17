package com.gilab.wjj.persistence.model;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class Building implements Entity {
    private long id;
    private int buildingNo;
    private Integer floor;
    private Integer roomNo;
    private String buildingAddress;
    private long ownerId;

    public Building(){}

    public Building(long id, int buildingNo, Integer floor, Integer roomNo, String buildingAddress, long ownerId) {
        this.id = id;
        this.buildingNo = buildingNo;
        this.floor = floor;
        this.roomNo = roomNo;
        this.buildingAddress = buildingAddress;
        this.ownerId = ownerId;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(int buildingNo) {
        this.buildingNo = buildingNo;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public String getBuildingAddress() {
        return buildingAddress;
    }

    public void setBuildingAddress(String buildingAddress) {
        this.buildingAddress = buildingAddress;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Building{" +
                "id=" + id +
                ", buildingNo=" + buildingNo +
                ", floor=" + floor +
                ", roomNo=" + roomNo +
                ", buildingAddress='" + buildingAddress + '\'' +
                ", ownerId=" + ownerId +
                '}';
    }

    public static class Builder{
        private long id;
        private int buildingNo;
        private Integer floor;
        private Integer roomNo;
        private String buildingAddress;
        private long ownerId;

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Builder buildingNo(int buildingNo){
            this.buildingNo = buildingNo;
            return this;
        }

        public Builder floor(Integer floor){
            this.floor = floor;
            return this;
        }

        public Builder roomNo(Integer roomNo){
            this.roomNo = roomNo;
            return this;
        }

        public Builder buildingAddress(String buildingAddress){
            this.buildingAddress = buildingAddress;
            return this;
        }

        public Builder ownerId(long ownerId){
            this.ownerId = ownerId;
            return this;
        }

        public Building build(){
            return new Building(id, buildingNo, floor, roomNo, buildingAddress, ownerId);
        }
    }
}
