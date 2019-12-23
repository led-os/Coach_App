package com.jsjlzj.wayne.entity.address;

import java.util.List;

public class MalAddressProvince {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean{
    private List<MalAddressProvice> provinceList;

        public List<MalAddressProvice> getProvinceList() {
            return provinceList;
        }

        public void setProvinceList(List<MalAddressProvice> provinceList) {
            this.provinceList = provinceList;
        }

        public class MalAddressProvice {
            private int id;
            private int parentId;
            private String name;
            private String shortName;
            private String lng;
            private String lat;
            private int sort;
            private List<MalAddressCity> cityList;

            public class MalAddressCity {
                private int id;
                private int parentId;
                private String name;
                private String shortName;
                private String lng;
                private String lat;
                private int sort;
                private List<MalAddressCounty> areaList;

                public class MalAddressCounty {
                    private int id;
                    private int parentId;
                    private String name;
                    private String shortName;
                    private String lng;
                    private String lat;
                    private int sort;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getParentId() {
                        return parentId;
                    }

                    public void setParentId(int parentId) {
                        this.parentId = parentId;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getShortName() {
                        return shortName;
                    }

                    public void setShortName(String shortName) {
                        this.shortName = shortName;
                    }

                    public String getLng() {
                        return lng;
                    }

                    public void setLng(String lng) {
                        this.lng = lng;
                    }

                    public String getLat() {
                        return lat;
                    }

                    public void setLat(String lat) {
                        this.lat = lat;
                    }

                    public int getSort() {
                        return sort;
                    }

                    public void setSort(int sort) {
                        this.sort = sort;
                    }
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getParentId() {
                    return parentId;
                }

                public void setParentId(int parentId) {
                    this.parentId = parentId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getShortName() {
                    return shortName;
                }

                public void setShortName(String shortName) {
                    this.shortName = shortName;
                }

                public String getLng() {
                    return lng;
                }

                public void setLng(String lng) {
                    this.lng = lng;
                }

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public List<MalAddressCounty> getAreaList() {
                    return areaList;
                }

                public void setAreaList(List<MalAddressCounty> areaList) {
                    this.areaList = areaList;
                }
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getShortName() {
                return shortName;
            }

            public void setShortName(String shortName) {
                this.shortName = shortName;
            }

            public String getLng() {
                return lng;
            }

            public void setLng(String lng) {
                this.lng = lng;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public List<MalAddressCity> getCityList() {
                return cityList;
            }

            public void setCityList(List<MalAddressCity> cityList) {
                this.cityList = cityList;
            }
        }}
}
