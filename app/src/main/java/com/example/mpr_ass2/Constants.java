package com.example.mpr_ass2;

import com.example.mpr_ass2.model.Product;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {

    public static String BASE_URL = "https://mpr-cart-api.herokuapp.com/products";


    public static Product[] products = {
            new Product(1, "https://cf.shopee.vn/file/beca50e46d2088fc5ad3c74aff5cc112",
                    "[Siêu HOT] Đèn Ngủ Chiếu Sao Tự Xoay 3D", 169000),
            new Product(2, "https://cf.shopee.vn/file/b0c4d1c4443fb7c2d9b97cd8681f444e",
                    "Đèn Ngủ 3D Led Nhiều Mẫu Hình Cực Đẹp - 3 màu (Được chọn hình)", 55000),
            new Product(3, "https://cf.shopee.vn/file/9333b4ea1c3df6693e9484487917b0c2",
                    "Đèn Ngủ Led Để Bàn Chân Gỗ Tự Nhiên [CMART.COM.VN]", 99000),
            new Product(4, "https://cf.shopee.vn/file/6df240e4782c481b88966ada56d92753",
                    "Đèn Ngủ 3D Led Nhiều Mẫu Hình Cực Đẹp - 3 màu- 001", 55000),
            new Product(5, "https://cf.shopee.vn/file/b4fb9f4fc4af2c52843a440134e907ef",
                    "[Siêu HOT] Đèn Ngủ Chiếu Sao Tự Xoay 3D", 169000),
            new Product(6, "https://cf.shopee.vn/file/47fa76fecd88f10eee9768dbd301ed95",
                    "Đồng hồ điện tử nam nữ Sports S03 thể thao, mẫu mới tuyệt đẹp, full chức năng, chống nước tốt", 23000),
            new Product(7, "https://cf.shopee.vn/file/78e4450b07162a4e233620efbdeebae2",
                    "Đồng hồ thời trang nữ Gogey dây da êm tay, mặt tròn nhỏ xinh xắn, chống trày xước tốt", 27000),
    };

    public static ArrayList<Product> listProduct(){
        return new ArrayList<>(Arrays.asList(products));
    }
}
