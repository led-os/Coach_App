package com.jsjlzj.wayne.entity.store;

import java.util.ArrayList;
import java.util.List;

public class MdlDict {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public class DataBean{
        private SalaryRequiredBean salary_required;
        private SalaryRequiredBean sex;
        private SalaryRequiredBean rest_time;
        private SalaryRequiredBean position_tipoff;
        private SalaryRequiredBean education_level;
        private SalaryRequiredBean work_overtime;
        private SalaryRequiredBean work_status;
        private SalaryRequiredBean staff_num;
        private SalaryRequiredBean environment_variable;
        private SalaryRequiredBean work_years;
        private SalaryRequiredBean cv_unsuitable;

        public SalaryRequiredBean getSalary_required() {
            return salary_required;
        }

        public void setSalary_required(SalaryRequiredBean salary_required) {
            this.salary_required = salary_required;
        }

        public SalaryRequiredBean getCv_unsuitable() {
            return cv_unsuitable;
        }

        public void setCv_unsuitable(SalaryRequiredBean cv_unsuitable) {
            this.cv_unsuitable = cv_unsuitable;
        }

        public SalaryRequiredBean getSex() {
            return sex;
        }

        public void setSex(SalaryRequiredBean sex) {
            this.sex = sex;
        }

        public SalaryRequiredBean getRest_time() {
            return rest_time;
        }

        public void setRest_time(SalaryRequiredBean rest_time) {
            this.rest_time = rest_time;
        }

        public SalaryRequiredBean getPosition_tipoff() {
            return position_tipoff;
        }

        public void setPosition_tipoff(SalaryRequiredBean position_tipoff) {
            this.position_tipoff = position_tipoff;
        }

        public SalaryRequiredBean getEducation_level() {
            return education_level;
        }

        public void setEducation_level(SalaryRequiredBean education_level) {
            this.education_level = education_level;
        }

        public SalaryRequiredBean getWork_overtime() {
            return work_overtime;
        }

        public void setWork_overtime(SalaryRequiredBean work_overtime) {
            this.work_overtime = work_overtime;
        }

        public SalaryRequiredBean getWork_status() {
            return work_status;
        }

        public void setWork_status(SalaryRequiredBean work_status) {
            this.work_status = work_status;
        }

        public SalaryRequiredBean getStaff_num() {
            return staff_num;
        }

        public void setStaff_num(SalaryRequiredBean staff_num) {
            this.staff_num = staff_num;
        }

        public SalaryRequiredBean getEnvironment_variable() {
            return environment_variable;
        }

        public void setEnvironment_variable(SalaryRequiredBean environment_variable) {
            this.environment_variable = environment_variable;
        }

        public SalaryRequiredBean getWork_years() {
            return work_years;
        }

        public void setWork_years(SalaryRequiredBean work_years) {
            this.work_years = work_years;
        }

        public class SalaryRequiredBean{
            private String code;
            private String name;
            private List<ItemsBean> items;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ItemsBean> getItems() {
                if(items==null)items=new ArrayList<>();
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }
        }

    }
}
