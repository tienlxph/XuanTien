<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Quản lý đợt giảm giá</h3>
<form:form method="post" action="/phieu-giam-gia/add" modelAttribute="pg" >

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

    <p>Người tạo:<form:select path="nhanVien">
        <form:option value="">-----</form:option>
        <form:options items="${nhanList}" itemValue="id" itemLabel="tenDayDu"/>
    </form:select>
    </p>

    <p>Ngày bắt đầu: <form:input type="date" value="${dg.ngayBatDau}"  path="ngayBatDau"/>

    </p>

    <p>Ngày kết thúc: <form:input type="date" value="${dg.ngayKetThuc}" path="ngayKetThuc"/>

    </p>

    <p>Trạng thái:
        <form:radiobutton path="trangThai" value="1"/>Còn hoạt động
        <form:radiobutton path="trangThai" value="0"/>Ngừng giảm giá

    </p>
    <p>So Tien Giam Tối Đa: <form:input type="text" path="sotiengiamgiatoida"/>
    <p>Số tiền giảm tối thiểu <form:input type="text" path="sotienapdungtoithieu"/>

    <button>ADD</button>



</form:form>
<table border="1">
    <tr>

        <th>Tên</th>
        <th>Ma</th>
        <th>Muc Giam Gia</th>
        <th>Kieu Giam Gia</th>
        <th>So Luong</th>
        <th>Nguoi Tao</th>
        <th>Ngày bắt đầu</th>
        <th>Ngày kết thúc</th>
        <th>Trạng thái</th>
        <th>Số tiền giảm tối đa</th>
        <th>Số tiền áp dụng tối thiểu</th>
        <th></th>
    </tr>
    <c:forEach items="${list}" var="pg">
        <tr>
            <td>${pg.ten}</td>
            <td>${pg.ma}</td>
            <td>${pg.mucgiamgia}</td>
            <td>${pg.kieugiamgia }</td>
            <td>${pg.soluong}</td>
            <td>${pg.nhanVien.tenDayDu}</td>
            <td>${pg.ngayBatDau}</td>
            <td>${pg.ngayKetThuc}</td>
            <td>${pg.trangThai ? "Còn hoạt động" : "Ngừng" }</td>
            <td>${pg.sotiengiamgiatoida}</td>
            <td>${pg.sotienapdungtoithieu}</td>
            <td>
                <a href="/view-update/${pg.id}">Update</a>
                <a      onclick="return confirm('Do  you want to delete')"
                        href="/pgg/delete/${pg.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<%--<c:forEach begin="0" end="${list.totalPages-1}" var="p">--%>
<%--    <a href="/dot-giam-gia/hien-thi?p=${p}">${p+1}</a>--%>
<%--</c:forEach>--%>
