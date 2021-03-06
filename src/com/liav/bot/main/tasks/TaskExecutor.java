package com.liav.bot.main.tasks;

import java.util.concurrent.Executor;

import com.liav.bot.main.Bot;

/**
 * Runnable which automatically calls {@link TaskPool#tick()} every
 * {@value TaskPool#UPDATE_RATE} ms.
 * <p>
 * <b>This {@link Runnable} requires the thread monitor to run. {@link Bot}
 * executes it in an {@link Executor}</b>
 * 
 * @author Liav
 *
 */
public class TaskExecutor implements Runnable {
	@Override
	public void run() {
		try {
			synchronized (this) {
				while (true) {
					wait(TaskPool.UPDATE_RATE);
					TaskPool.tick();
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
