package net.binxly.constructor.services;

import net.binxly.constructor.models.BuildRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class OrchestrationService {

    static Logger log = LoggerFactory.getLogger(OrchestrationService.class);

    @Inject
    ConfigConstructionService configConstructionService;

    @Inject
    DirectoryService directoryService;

    public void orchestrate(BuildRequest buildRequest) {

        log.info("begin orchestration");
        try {
            this.directoryService.createBuildDir(buildRequest.getId());
            this.configConstructionService.construct(buildRequest.getId(), buildRequest.getProjectName());
            this.directoryService.cleanup(buildRequest.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
