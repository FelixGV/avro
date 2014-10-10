package org.apache.avro.repo;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * This {@link Repository} implementation stores its state using Zookeeper.
 *
 * It requires the avro.repo.zookeeper-ensemble configuration property. This is
 * a comma-separated list of host:port addresses. Each address can also be suffixed
 * by a namespace, i.e.: zk.1:2181/schemas,zk.2:2181/schemas,zk.3:2181/schemas
 *
 * This Repository is meant to be highly available, meaning that multiple instances
 * can share the same Zookeeper ensemble and synchronize their state through it.
 */
public class ZookeeperRepository implements Repository {
    private static final String ROOT_ZK_PATH = "/schema-repo";
    private static final Integer ZK_SESSION_TIMEOUT = 5000;

    ZooKeeper zk;
    Watcher zkWatcher;

    @Inject
    public ZookeeperRepository(@Named("avro.repo.zookeeper-ensemble") String zkEnsemble,
                               ValidatorFactory validators) {
        zkWatcher = new Watcher() {
            @Override
            public void process(WatchedEvent event) {

            }
        };

        try {
            zk = new ZooKeeper(zkEnsemble, ZK_SESSION_TIMEOUT, zkWatcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Attempts to create a Subject with the given name and config.
     *
     * @param subjectName The name of the subject. Must not be null.
     * @param config      The subject configuration. May be null.
     * @return The newly created Subject, or an equivalent one if already created.
     *         Does not return null.
     * @throws NullPointerException if subjectName is null
     */
    @Override
    public Subject register(String subjectName, SubjectConfig config) {
        return null;
    }

    /**
     * Returns the subject if it exists, null otherwise.
     *
     * @param subjectName the subject name
     * @return The subject if it exists, null otherwise.
     */
    @Override
    public Subject lookup(String subjectName) {
        return null;
    }

    /**
     * List all subjects. Does not return null.
     */
    @Override
    public Iterable<Subject> subjects() {
        return null;
    }
}
