package com.example.SV_Market.utils;

import java.util.HashMap;
import java.util.Map;

public class TransactionUtil {
    Map<String,String> responseTransactions = new HashMap<String,String>();

    public TransactionUtil() {
        responseTransactions.put("00","Giao dịch thành công");
        responseTransactions.put("07","Trừ tiền thành công. Giao dịch bị nghi ngờ (liên quan tới lừa đảo, giao dịch bất thường).");
        responseTransactions.put("09","Giao dịch không thành công do: Thẻ/Tài khoản của khách hàng chưa đăng ký dịch vụ InternetBanking tại ngân hàng.");
        responseTransactions.put("10","Giao dịch không thành công do: Khách hàng xác thực thông tin thẻ/tài khoản không đúng quá 3 lần");
        responseTransactions.put("11","Giao dịch không thành công do: Đã hết hạn chờ thanh toán. Xin quý khách vui lòng thực hiện lại giao dịch.");
        responseTransactions.put("12","Giao dịch không thành công do: Thẻ/Tài khoản của khách hàng bị khóa.");
        responseTransactions.put("13","Giao dịch không thành công do Quý khách nhập sai mật khẩu xác thực giao dịch (OTP). Xin quý khách vui lòng thực hiện lại giao dịch.");
        responseTransactions.put("24","Giao dịch không thành công do: Khách hàng hủy giao dịch");
        responseTransactions.put("51","Giao dịch không thành công do: Tài khoản của quý khách không đủ số dư để thực hiện giao dịch.");
        responseTransactions.put("65","Giao dịch không thành công do: Tài khoản của Quý khách đã vượt quá hạn mức giao dịch trong ngày.");
        responseTransactions.put("75","Ngân hàng thanh toán đang bảo trì.");
        responseTransactions.put("79","Giao dịch không thành công do: KH nhập sai mật khẩu thanh toán quá số lần quy định. Xin quý khách vui lòng thực hiện lại giao dịch");
        responseTransactions.put("99","Các lỗi khác (lỗi còn lại, không có trong danh sách mã lỗi đã liệt kê)");
    }
    public Map<String, String> getResponseTransactions() {
        return responseTransactions;
    }

    public void setResponseTransactions(Map<String, String> responseTransactions) {
        this.responseTransactions = responseTransactions;
    }

    public String getType(String key){
        return responseTransactions.get(key);
    }
}
