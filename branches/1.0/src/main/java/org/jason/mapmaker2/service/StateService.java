package org.jason.mapmaker2.service;

import org.jason.mapmaker2.model.State;

/**
 * @author Jason Ferguson
 */
public interface StateService extends BasicServiceOperations<State> {

    State getByName(String name);
}
