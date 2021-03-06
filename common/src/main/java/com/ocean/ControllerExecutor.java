package com.ocean;

/**
 * 控制层执行抽象类
 *
 * @author guodahai
 * @version 2018/4/17 上午11:08
 */
public abstract class ControllerExecutor<R, T> {

    private T[] param;

    public ControllerExecutor(T... param) {
        this.param = param;
    }

    public abstract void checkParam() throws ServiceException;

    public abstract R executeService() throws ServiceException;

    public ResponseResult<R> execute(T... param) throws ServiceException {
        ResponseResult<R> result = new ResponseResult<>();
        try {
            R r = executeService();
            if (r instanceof Boolean) {
                Boolean b = (Boolean) r;
                result.setSuccess(b);
            } else {
                result.setData(r);
            }
        } catch (ServiceException e) {
            result.setSuccess(false);
            result.setErrorCode(e.getErrorCode().getCode());
            result.setErrorMessage(e.getErrorMsg());
        }
        return result;
    }

    public T[] getParam() {
        return param;
    }

    public void setParam(T[] param) {
        this.param = param;
    }
}
