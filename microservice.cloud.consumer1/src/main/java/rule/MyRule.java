package rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

public class MyRule extends AbstractLoadBalancerRule {


    int total = 0;
    int currentIndex = 0;

    public Server choose(Object key){

        ILoadBalancer lb = this.getLoadBalancer();

        if(lb == null){
            return null;
        }
        Server server = null;

        while (server == null){

            if(Thread.interrupted()){
                return null;
            }

            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if(serverCount==0){
                return null;
            }

//            int total = 0;
//            int currentIndex = 0;

            if(total<5){
                server = upList.get(currentIndex);
                total++;
            }else{
                total = 0;
                currentIndex++;

                if(currentIndex>= upList.size()){
                    currentIndex=0;
                }
            }

            if(server==null){
                Thread.yield();
                continue;
            }

            if(server.isAlive()){
                return (server);
            }

            server = null;
            Thread.yield();
        }

        return server;
    }

    public void initWithNiwsConfig(IClientConfig var1){

    }
}
