import { environment } from '../../environments/environment';

export class BaseConfig {
    static baseUrl = environment.props.host;
}
