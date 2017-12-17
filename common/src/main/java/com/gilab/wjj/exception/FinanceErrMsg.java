package com.gilab.wjj.exception;

import java.text.MessageFormat;

/**
 * FinanceErrMsg
 * Author: yuankui
 * Desc:
 * Change Log:
 * 2017/10/31 - created by yuankui
 */
public enum FinanceErrMsg implements ErrMsg {
    METHOD_NOT_SUPPORTED(70000, "Method is not supported in current version"),
    CONFIG_MISSING(70001, "Configuration is missing"),
    CONFIG_INVALID(70002, "Configuration is invalid"),
    SYSTEM_IO_FAILURE(70003, "System IO failure"),
    PRECONDITION_MISMATCH(70004, "Precondition mismatch"),
    SERVICE_ALREADY_STARTED(70005, "Service already started"),
    PLUGIN_CREATE_FAILURE(70006, "Plugin creation failure"),
    PLUGIN_EXEC_FAILURE(70007, "Plugin exec failure"),
    PLUGIN_LOAD_FAILURE(70008, "Plugin load failure"),
    INFO_LOOKUP_MISS(70009, "Information required but lookup missing"),
    RESOURCE_NOT_CAPABLE(70010, "Resource not capable"),
    DECRYPT_FAILURE(70011, "Decryption failure"),
    INVALID_TRANSACTION(70012, "Invalid transaction detected"),
    AUTHENTICATION_FAILURE(70013, "Authentication failure"),
    ACCESS_DENIED(70014, "Access denied"),
    EXTERNAL_SERVICE_FAILURE(70015, "External service failure"),
    CLIENT_FAILURE(70016, "Client failure"),
    AUTHORIZATION_FAILURE(70017, "Authorization failure"),
    LICENSE_ERROR(70018, "License error"),

    NAMED_METHOD_NOT_SUPPORTED(71000, "Method[{0}] is not supported i current version", true),
    NAMED_CONFIG_MISSING(71001, "Configuration[{0}] is missing", true),
    NAMED_CONFIG_INVALID(71002, "Configuration[{0}] is invalid", true),
    NAMED_SYSTEM_IO_FAILURE(71003, "System IO[{0}] failed", true),
    NAMED_PRECONDITION_MISMATCH(71004, "Precondition mismatch: {0}", true),
    NAMED_SERVICE_ALREADY_STARTED(71005, "Service already started: {0}", true),
    NAMED_PLUGIN_CREATE_FAILURE(71006, "Plugin creation failure: {0}", true),
    NAMED_PLUGIN_EXEC_FAILURE(71007, "Plugin[{0}] exec failure", true),
    NAMED_PLUGIN_LOAD_FAILURE(71008, "Plugin[{0}] load failure", true),
    NAMED_INFO_LOOKUP_MISS(71009, "Information[{0}] required but lookup missing", true),
    NAMED_RESOURCE_NOT_CAPABLE(71010, "Resource[{0}] not capable", true),
    NAMED_DECRYPT_FAILURE(71011, "Decrypt[{0}] failure", true),
    NAMED_INVALID_TRANSACTION(71012, "Invalid transaction[{0}] detected", true),
    NAMED_AUTHENTICATION_FAILURE(71013, "Authentication failure[{0}]", true),
    NAMED_ACCESS_DENIED(71014, "Access[{0}] denied", true),
    NAMED_EXTERNAL_SERVICE_FAILURE(71015, "External service[{0}] failure", true),
    NAMED_CLIENT_FAILURE(71016, "Client failure: {0}", true),
    NAMED_AUTHORIZATION_FAILURE(71017, "Authorization failure: {0}", true),
    NAMED_LICENSE_ERROR(71018, "License error: {0}", true);

    private MsgPrefix prefix = MsgPrefix.COMMON;
    private int errCode;
    private String msg;
    private MessageFormat format;

    FinanceErrMsg(int errCode, String msg) {
        this(errCode, msg, false);
    }

    FinanceErrMsg(int errCode, String msg, boolean format) {
        this.errCode = errCode;
        this.msg = msg;
        this.format = format ? new MessageFormat(msg) : null;
    }

    @Override
    public MsgPrefix getPrefix() {
        return prefix;
    }

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public MessageFormat getFormat() {
        return format;
    }
}
