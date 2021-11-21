#include <iostream>
#include "ROC_generated.h"
#include "roc-builder.h"

extern "C" {
#include "unp.h"

void sig_chld(int signo) {
	pid_t	pid;
	int		stat;

	pid = wait(&stat);
	printf("child %d terminated\n", pid);
	return;
}
}

using namespace ROC; 

void roc_getter(int);


int main(int argc, char **argv) {
	int					listenfd, connfd;
	pid_t				childpid;
	socklen_t			clilen;
	struct sockaddr_in	cliaddr, servaddr;

	listenfd = Socket(AF_INET, SOCK_STREAM, 0);

	bzero(&servaddr, sizeof(servaddr));
	servaddr.sin_family      = AF_INET;
	servaddr.sin_port        = htons(62700);
    inet_pton(AF_INET, "127.0.0.1", (void*) &servaddr.sin_addr);

	Bind(listenfd, (SA *) &servaddr, sizeof(servaddr));

	Listen(listenfd, LISTENQ);

	Signal(SIGCHLD, sig_chld);

	for ( ; ; ) {
		clilen = sizeof(cliaddr);
		if ( (connfd = accept(listenfd, (SA *) &cliaddr, &clilen)) < 0) {
			if (errno == EINTR)
				continue;		/* back to for() */
			else
				err_sys("accept error");
		}

		if ( (childpid = Fork()) == 0) {	/* child process */
			Close(listenfd);	/* close listening socket */
            roc_getter(connfd);
            std::cout << "#####" << std::endl;
			exit(0);
		}
        Close(connfd);			/* parent closes connected socket */
	}
}

void roc_getter(int sockfd) {
    ssize_t n; 
    ssize_t max_len = 1024;
    char buf[max_len];
again:
	while ( (n = read(sockfd, buf, max_len)) > 0) {
        if (n < max_len) {
            std::cout << "Less than message length, read again" << std::endl;
            ssize_t cur = n;
            while (cur < max_len) {
                ssize_t n1 = read(sockfd, buf+cur, max_len-cur);
                cur += n1;
            }
            std::cout << "Complete read again" << std::endl;
        }
        uint8_t* buf_pointer = (uint8_t*) buf; 
        auto roc_info = GetROCInfo(buf_pointer);
        auto roc_type = roc_info->info_type();
        auto delay = roc_info->delay();
        //std::cout << "delay = " << delay->str() <<  std::endl;
        if (roc_type == ROCType_Create) {
            auto creation_info = static_cast<const CreationInfo*>(roc_info->info());
            auto id = creation_info->uav_id();
            auto lat = creation_info->latitude();
            auto lng = creation_info->longitude();
            auto masterId = creation_info->master_id();
            //std::cout << "Creation Info: " << std::endl;
            //std::cout << "uav id = " << id->str() << std::endl;
            //std::cout << "lat = " << lat->str() << std::endl;
            //std::cout << "lng = " << lng->str() << std::endl;
            //std::cout << "master id = " << masterId << std::endl;
        }
        else if (roc_type == ROCType_Action) {
            auto action_info = static_cast<const ActionInfo*>(roc_info->info());
            auto id = action_info->uav_id();
            auto lat = action_info->latitude();
            auto lng = action_info->longitude();
            //std::cout << "Action Info: " << std::endl;
            //std::cout << "uav id = " << id->str() << std::endl;
            //std::cout << "lat = " << lat->str() << std::endl;
            //std::cout << "lng = " << lng->str() << std::endl;
        }
        else if (roc_type == ROCType_SINRReq) {
            auto sinr_req = static_cast<const SINRReq*>(roc_info->info());
            auto id = sinr_req->uav_id();
            //std::cout << "SINRReq" << std::endl;
            //std::cout << "Uav id = " << id->str() << std::endl;
            //std::cout << "delay = " << delay->str() <<  std::endl;
            ROCBuilder rocb;
            std::pair<uint8_t*, int> package = rocb.buildSINRResp ("1", "2", "3", "4", "5", "6");
            int size = htonl(package.second);
            int writeN = write(sockfd, &size, sizeof(size));
            writeN = write(sockfd, package.first, package.second);
        } 
        else if (roc_type == ROCType_Delete) {
            auto deletion_info = static_cast<const DeletionInfo*>(roc_info->info());
            auto id = deletion_info->uav_id();
            //std::cout << "Uav id = " << id->str() << std::endl;
        }
        else if (roc_type == ROCType_ThroughputReq) {
            auto throughput_req = static_cast<const ThroughputReq*>(roc_info->info());
            auto id = throughput_req->uav_id();
            //std::cout << "Throughput Req" << "Uav id = " << id->str() << std::endl;
            ROCBuilder rocb;
            std::pair<uint8_t*, int> package = rocb.buildThroughputResp ("1", "2", "3");
            int size = htonl(package.second);
            int writeN = write(sockfd, &size, sizeof(size));
            writeN = write(sockfd, package.first, package.second);
        }
        else if (roc_type == ROCType_ThroughputResp) {
            auto throughput_resp = static_cast<const ThroughputResp*>(roc_info->info());
            auto id = throughput_resp->uav_id();
            auto thrpt = throughput_resp->throughput();
            //std::cout << "Uav id = " << id->str() << " Throughput = " << thrpt->str() << std::endl;
        }
        else {
            //std::cout << "Not a roc message" << std::endl; 
        }
    }
    if (n < 0 && errno == EINTR)
        goto again;
    else if (n < 0)
        err_sys("roc_getter: read error");
}


