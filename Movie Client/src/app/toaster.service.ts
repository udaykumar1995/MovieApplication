import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import * as _ from 'underscore';

@Injectable()
export class ToasterService {
    constructor(private toastr: ToastrService) {}
    successMessage(message:any,isHtml:boolean = false) {
        if(isHtml){
            message = this.generateHtmlList(message);
        }
        this.toastr.success(message);
    }
    errorMessage(message:any,isHtml:boolean = false,isPluck:boolean = false,keyToPluck:string = null){
        if(isHtml){
            if(!isPluck){
                message = this.generateHtmlList(message);
            }else{
                let errorMsgs = _.pluck(message,keyToPluck);
                message = this.generateHtmlList(errorMsgs);
            }
        }
        if(message){
            this.toastr.error(message);
        }
        
    }
    warningMessage(message:any,isHtml:boolean = false) {
        if(isHtml){
            message = this.generateHtmlList(message);
        }
        this.toastr.warning(message);
    }
    generateHtmlList(message){
        let msgStr = "";
        if(_.isArray(message)){
            msgStr = "<ul>";
            for(let str of message){
                msgStr+="<li>"+str+"</li>";
            }
            msgStr+="</ul>";
        }
        return msgStr;
        
    }
}