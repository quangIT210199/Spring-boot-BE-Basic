package com.example.springbootquang.exception;

import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

//@RestControllerAdvice
public class ApiExceptionHandler {
    /*
     * Tất cả các Exception không được khai báo sẽ được xử lí tại đây
     * Các statusCode
     * 1xx - infomational: Request đã được nhận và tiến hành quá trình tiếp theo
     * 2xx - successful: yêu cầu đã được nhận thành công và được chấp nhận
     * 3xx - redirection (chuyển hướng): cần phải thực hiện thêm hành động để hoàn thành yêu cầu
     * 4xx - client error: yêu cầu chứa cú pháp sai hoặc không thể thực hiện được
     * 5xx - server error: máy chủ không thực hiện được yêu cầu rõ ràng là hợp lệ
     * */

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handlerAllException(Exception ex, WebRequest request){
        //quá trình kiểm tra lỗi xảy ra ở đây

        return new ErrorMessage(500, ex.getLocalizedMessage());
    }

    /**
     * IndexOutOfBoundsException sẽ được xử lý riêng tại đây
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage newExceptionHandler(Exception ex, WebRequest request){

        return new ErrorMessage(400, "BAD REQUEST");
    }

    //404: NOT FOUND
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundExceptionHandler(Exception ex, WebRequest request){
        return new ErrorMessage(404, "Không tìm thấy");
    }
}
