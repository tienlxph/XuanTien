
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form:form method="post" action="/phieu-giam-gia/update/${id}" modelAttribute="pg" >

    <p>
        Tên: <form:input type="text" path="ten"/>

    </p>
    <p>Ma: <form:input type="text" path="ma"/>

    </p>
    <p>Mức giảm giá: <form:input type="text" path="mucgiamgia"/>

    </p>
    <p>Kiểu giảm giá:
        <form:radiobutton path="kieugiamgia" value="1"/>Tiền mặt
        <form:radiobutton path="kieugiamgia" value="0"/>%

    </p>
    <p>SO luong: <form:textarea type="text" path="soluong"/>

    </p>

<%--    <p>Người tạo:<form:select path="nhanVien">--%>
<%--        <form:option value="">-----</form:option>--%>
<%--        <form:options items="${nhanList}" itemValue="id" itemLabel="tenDayDu"/>--%>
<%--    </form:select>--%>
<%--    </p>--%>

    <p>Ngày bắt đầu: <form:input type="date"  path="ngayBatDau" value="${pg.ngayBatDau}" />

    </p>

    <p>Ngày kết thúc: <form:input type="date"  path="ngayKetThuc" value="${pg.ngayKetThuc}" />

    </p>

<%--    <p>Trạng thái:--%>
<%--        <form:radiobutton path="trangThai" value="1"/>Còn hoạt động--%>
<%--        <form:radiobutton path="trangThai" value="0"/>Ngừng giảm giá--%>

<%--    </p>--%>
    <p>So Tien Giam Tối Đa: <form:input type="text" path="sotiengiamgiatoida"/>
    <p>Số tiền giảm tối thiểu <form:input type="text" path="sotienapdungtoithieu"/>

    <button>ADD</button>
</form:form>
