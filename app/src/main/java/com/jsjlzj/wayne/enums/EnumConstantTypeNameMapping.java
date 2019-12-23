package com.jsjlzj.wayne.enums;

/**
 * Created by Administrator on 2018/6/8.
 */

public class EnumConstantTypeNameMapping {

    public static String getMessageTypeName(@EnumMessageType.MsgType int key) {
        String name = "";
        switch (key) {
            case EnumMessageType.PREPARE_MATERIAL:
                name = "备料";
                break;
            case EnumMessageType.REPLACE_MOULD:
                name = "换模";
                break;
            case EnumMessageType.STATEMENT:
                name = "报表";
                break;
            case EnumMessageType.FAULT:
                name = "故障";
                break;
            case EnumMessageType.INCOME:
                name = "收入";
                break;
            case EnumMessageType.INVITE_ME_JOIN_TEAM:
                name = "邀请";
                break;
            case EnumMessageType.JOIN_TEAM_APPLY:
                name = "申请加入";
                break;
            case EnumMessageType.JOIN_TEAM_SUCCESS:
                name = "加入成功";
                break;
            case EnumMessageType.REPLY_FEEDBACK:
                name = "反馈回复";
                break;
            case EnumMessageType.PENDING_ORDER:
                name = "挂单";
                break;
            case EnumMessageType.POPULARIZE:
                name = "推广";
                break;
            case EnumMessageType.PENDING_AGENT:
                name = "【唯您专享】";
                break;

        }

        return name;
    }

    public static String getSexTypeName(int key) {

        return key == 1 ? "男" : "女";
    }

    public static String getTeamRoleTypeName(int[] keys) {
        StringBuilder sb = new StringBuilder();
        String name = "";
        for (int key : keys) {
            switch (key) {
                case 1:
                    name = "团队管理员";
                    break;
                case 2:
                    name = "PMC";
                    break;
                case 3:
                    name = "高管";
                    break;
                case 4:
                    name = "车间主管";
                    break;
                case 5:
                    name = "组长";
                    break;
                case 6:
                    name = "换模师";
                    break;
                case 7:
                    name = "备料师";
                    break;
                case 8:
                    name = "机械师";
                    break;
            }
            sb.append(name).append(",");
        }

        if (sb.length() > 1)
            sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public static String getMachineOperationTypeName(@EnumMachineOperationStatus.Status int key) {
//            machineOperationTypeMapperName.put(EnumMachineOperationStatus.FAULT, "故障");

        String name = "未安排";
        switch (key) {
            case EnumMachineOperationStatus.WAIT_4_REPLACE_MOULD:
                name = "待换模";//待加工
                break;
            case EnumMachineOperationStatus.DOING_REPLACE_MOULD:
                name = "换模中";
                break;
            case EnumMachineOperationStatus.WAIT_4_PROCESS:
                name = "待加工";//换模完成
                break;
            case EnumMachineOperationStatus.PROCESSING:
                name = "加工中";
                break;
            case EnumMachineOperationStatus.COMPLETED:
                name = "已完成";
                break;
            case EnumMachineOperationStatus.HANGUP:
                name = "挂单中";
                break;


            case EnumMachineOperationStatus.INTEND_WAIT:
                name = "准备中等待";
                break;
            case EnumMachineOperationStatus.REPLACE_MOULD_WAITING:
                name = "换模等待";
                break;
            case EnumMachineOperationStatus.WAIT_4_PROCESS_WAIT:
                name = "待加工等待";
                break;
            case EnumMachineOperationStatus.PROCESS_WAITING:
                name = "加工种等待";
                break;
            case EnumMachineOperationStatus.PROCESS_FAULT:
                name = "加工故障";
                break;
            case EnumMachineOperationStatus.REPLACE_MOULD_FAULT:
                name = "换模故障";
                break;
        }
        return name;
    }

    public static String getProcedureTypeName(int key) {
        String name = "";
        switch (key) {
            case 1:
                name = "排产中";
                break;
            case 2:
                name = "准备中";
                break;
            case 3:
                name = "加工中";
                break;
            case 4:
                name = "已完成";
                break;
            case 5:
                name = "已跳过";
                break;
        }

        return name;
    }

    /**
     * 工单进度
     */
    public static String getWorkOrderTypeName(int status) {
        String name = "";
        switch (status) {
            case EnumWorkOrderStatus.ALLOCATE_WAIT:
                name = "待分配";
                break;
            case EnumWorkOrderStatus.ALLOCATING:
                name = "分配确认";
                break;
            case EnumWorkOrderStatus.ALLOCATED:
                name = "已分配";
                break;
            case EnumWorkOrderStatus.SCHEDULIING:
                name = "排产确认";
                break;
            case EnumWorkOrderStatus.COMPLETE:
                name = "加工完成";
                break;
        }
        return name;
    }

    public static int getWorkOrderStatus(String name) {
        int status = -1;
        switch (name) {
            case "待分配":
                status = EnumWorkOrderStatus.ALLOCATE_WAIT;
                break;
            case "分配确认":
                status = EnumWorkOrderStatus.ALLOCATING;
                break;
            case "已分配":
                status = EnumWorkOrderStatus.ALLOCATED;
                break;
            case "排产确认":
                status = EnumWorkOrderStatus.SCHEDULIING;
                break;
            case "加工完成":
                status = EnumWorkOrderStatus.COMPLETE;
                break;
        }
        return status;
    }


    public static String getWorkOrderPrepareMaterialDetailMachineTypeName(int key) {
        String name = "";
        switch (key) {
            case 2:
                name = "待换模";
                break;
            case 3:
                name = "换模中";
                break;
            case 4:
                name = "待加工";
                break;
            case 5:
                name = "加工中";
                break;
            case 6:
                name = "已完成";
                break;
            case 7:
                name = "故障";
                break;
            case 8:
                name = "准备中等待";
                break;
            case 9:
                name = "换模中等待";
                break;
            case 10:
                name = "待加工等待";
                break;
            case 11:
                name = "加工";
                break;
        }

        return name;
    }

    public static String getPrepareMaterialTypeName(@EnumPrepareMaterial.Status int key) {
        String name = "";
        switch (key) {
            case EnumPrepareMaterial.WAITING:
                name = "待备料";
                break;
            case EnumPrepareMaterial.DOING_PREPARE_MATERIAL:
                name = "备料中";
                break;
            case EnumPrepareMaterial.LACK_MATERIAL:
                name = "缺料";
                break;
            case EnumPrepareMaterial.COMPLETE:
                name = "备料完成";
                break;
        }

        return name;
    }

    public static String getReportTypeName(@EnumRecordCheckReportType.Type int key) {
        String name = "";
        switch (key) {
            case EnumRecordCheckReportType.WAIT_REPORT:
                name = "等待报表";
                break;
            case EnumRecordCheckReportType.REPLACE_MOULD:
                name = "换模报表";
                break;
            case EnumRecordCheckReportType.END_PRODUCE_REPORT:
                name = "结产报表";
                break;
        }

        return name;
    }

    public static String getWorkOrderScheduleMachineTypeName(int key) {
        String name = "";
        switch (key) {
            case 0:
                name = "待排产";
                break;
            case 1:
                name = "排产中";
                break;
            case 2:
                name = "准备中";
                break;
            case 3:
                name = "换模中";
                break;
            case 4:
                name = "待加工";
                break;
            case 5:
                name = "加工中";
                break;
            case 6:
                name = "已完成";
                break;
            case 7:
                name = "故障";
                break;
            case 8:
                name = "准备中等待";
                break;
            case 9:
                name = "换模中等待";
                break;
            case 10:
                name = "待加工等待";
                break;
        }

        return name;
    }

    public static boolean isWaitWork(int status) {
        if (status == EnumMachineOperationStatus.WAIT_4_REPLACE_MOULD ||
                status == EnumMachineOperationStatus.WAIT_4_PROCESS) return true;
        return false;
    }

    public static boolean isChangerWorkTime(int status) {
        if (status == EnumMachineOperationStatus.WAIT_4_REPLACE_MOULD ||
                status == EnumMachineOperationStatus.DOING_REPLACE_MOULD ||
                status == EnumMachineOperationStatus.REPLACE_MOULD_WAITING) return true;
        return false;
    }

    public static boolean isMechanicWorkTime(int status) {
        if (status == EnumMachineOperationStatus.WAIT_4_PROCESS ||
                status == EnumMachineOperationStatus.PROCESSING ||
                status == EnumMachineOperationStatus.PROCESS_WAITING) return true;
        return false;
    }

    public static boolean isProcessingUsedTime(int status) {
        if (status == EnumMachineOperationStatus.PROCESSING) return true;
        return false;
    }
    public static boolean isChangerUsedTime(int status) {
        if (status == EnumMachineOperationStatus.DOING_REPLACE_MOULD) return true;
        return false;
    }

    public static boolean isHangUpTime(int status) {
        if (status == EnumMachineOperationStatus.WAIT_4_REPLACE_MOULD ||
                status == EnumMachineOperationStatus.WAIT_4_PROCESS) return true;
        return false;
    }

}
