package com.hf.ciker.core.aop.core;

import com.hf.ciker.commons.exceptions.CikerException;

/**
 * @dasc
 * @author: ciker
 * @date: 2020/4/16
 */
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws CikerException;
}
